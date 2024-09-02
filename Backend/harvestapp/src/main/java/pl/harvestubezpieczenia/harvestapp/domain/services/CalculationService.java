package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.*;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.CalcPersonMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.*;
import pl.harvestubezpieczenia.harvestapp.domain.ports.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CalculationService {

    private final CalculationRepo calculationRepo;
    private final ApkQuestionRepo apkQuestionRepo;
    private final ApkCalculationRepo apkCalculationRepo;
    private final TerytRepo terytRepo;
    private final CalcPersonMapper calcPersonMapper;
    private final SoilClassRepo soilClassRepo;
    private final CropKindRepo cropKindRepo;
    private final CropVarietyRepo cropVarietyRepo;
    private final CoverRepo coverRepo;
    private final CropRepo cropRepo;
    private final LandRepo landRepo;
    private final LivestockKindRepo livestockKindRepo;
    private final LivestockRepo livestockRepo;

    public CalculationService(CalculationRepo calculationRepo, ApkQuestionRepo apkQuestionRepo, ApkCalculationRepo apkCalculationRepo, TerytRepo terytRepo, CalcPersonMapper calcPersonMapper, SoilClassRepo soilClassRepo, CropKindRepo cropKindRepo, CropVarietyRepo cropVarietyRepo, CoverRepo coverRepo, CropRepo cropRepo, LandRepo landRepo, LivestockKindRepo livestockKindRepo, LivestockRepo livestockRepo) {
        this.calculationRepo = calculationRepo;
        this.apkQuestionRepo = apkQuestionRepo;
        this.apkCalculationRepo = apkCalculationRepo;
        this.terytRepo = terytRepo;
        this.calcPersonMapper = calcPersonMapper;
        this.soilClassRepo = soilClassRepo;
        this.cropKindRepo = cropKindRepo;
        this.cropVarietyRepo = cropVarietyRepo;
        this.coverRepo = coverRepo;
        this.cropRepo = cropRepo;
        this.landRepo = landRepo;
        this.livestockKindRepo = livestockKindRepo;
        this.livestockRepo = livestockRepo;
    }

    public ResponseEntity<Integer> startNewCalculation() {
        int id = calculationRepo.startNewCalculation();
        if(id > 0) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> addInsurancePeriod(CalcInsurancePeriodDto dto, Integer id) {
        try {
            Calculation calculation = calculationRepo.getCalculationById(id);

            calculation.setDataPoczatkuOchrony(dto.getDataPoczatkuOchrony().toLocalDate());
            calculation.setDataKoncaOchrony(dto.getDataKoncaOchrony().toLocalDate());

            calculationRepo.saveCalculation(calculation);

            for (CalcApk apk : dto.getApk()) {
                ApkCalculation apkCalculation = apkCalculationRepo.getApkCalculationByIds(calculation.getIdKalkulacja(), apk.getIdApk());

                if (apkCalculation == null) {
                    apkCalculation = new ApkCalculation();
                }
                ApkQuestion apkQuestion = apkQuestionRepo.findApkById(apk.getIdApk());

                apkCalculation.setKalkulacja(calculation);
                apkCalculation.setApk(apkQuestion);
                apkCalculation.setOdpowiedz(apk.isApkOdpowiedz());

                apkCalculationRepo.saveApkCalculation(apkCalculation);

            }

            return new ResponseEntity<>("Successfully saved data to the Database", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> addPersonalData(CalcPersonalDataDto dto, Integer id) {
        try {
            Calculation calculation = calculationRepo.getCalculationById(id);
            Teryt policyHolderTeryt = terytRepo.getTeryt(dto.getUbezpieczajacy().getTeryt());

            User policyHolder = calcPersonMapper.mapToEntity(dto.getUbezpieczajacy(), policyHolderTeryt);
            calculation.setUbezpieczajacy(policyHolder);

            if (dto.getUbezpieczajacy().equals(dto.getUbezpieczony())) {
                calculation.setUbezpieczony(policyHolder);
            } else {
                Teryt policyInsuredTeryt = terytRepo.getTeryt(dto.getUbezpieczony().getTeryt());
                User policyInsured = calcPersonMapper.mapToEntity(dto.getUbezpieczony(), policyInsuredTeryt);
                calculation.setUbezpieczony(policyInsured);
            }
            calculationRepo.saveCalculation(calculation);

            return new ResponseEntity<>("Successfully saved data to the Database", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public ResponseEntity<String> addCrop(CalcCropDto dto, Integer id) {
        Calculation calculation = calculationRepo.getCalculationById(id);
        Crop crop = new Crop();

        SoilClass soilClass = soilClassRepo.getSoilClassById(dto.getIdKlasaGleby());
        CropKind cropKind = cropKindRepo.getCropKindById(dto.getIdRodzajUprawy());
        CropVariety cropVariety = cropVarietyRepo.getCropVarietyById(dto.getIdGatunek());

        crop.setKlasaGleby(soilClass);
        crop.setRodzajUprawy(cropKind);
        crop.setGatunek(cropVariety);

        for(int i: dto.getRyzyka()){
            Cover cover = coverRepo.getCoverById(i);
            crop.getOchrony().add(cover);
        }

        crop.setCzyNasienna(dto.isCzyNasienna());
        crop.setPowierzchnia(dto.getPowierzchnia());
        crop.setWartosc(dto.getWartosc());
        crop.setWartoscRynkowa(crop.getRodzajUprawy().getWartoscUbezpieczenia().wartoscRynkowa() * dto.getPowierzchnia());
        crop.setSumaUbezpieczenia((int) Math.round((dto.getWartosc() * dto.getPowierzchnia())));

        crop.setKalkulacja(calculation);
        cropRepo.saveCrop(crop);

        for(CalcLandDto cl: dto.getDzialki()){
            Teryt teryt = terytRepo.getTeryt(cl.getTeryt());

            Land land = new Land();

            land.setUprawa(crop);
            land.setTeryt(teryt);

            land.setObreb(cl.getObreb());
            land.setCzyPoprawna(cl.isCzyPoprawna());
            land.setNumerDzialki(cl.getNumerDzialki());
            land.setKodObrebu(cl.getKodObrebu());

            landRepo.saveLand(land);
        }

        return new ResponseEntity<>("Successfully saved data to the Database", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> modifyCrop(CalcCropDto dto, int idCalc, int idCrop) {
        Crop crop = cropRepo.getCropById(idCrop);
        if (crop == null) {
            return new ResponseEntity<>("Crop not found", HttpStatus.NOT_FOUND);
        }

        SoilClass soilClass = soilClassRepo.getSoilClassById(dto.getIdKlasaGleby());
        CropKind cropKind = cropKindRepo.getCropKindById(dto.getIdRodzajUprawy());
        CropVariety cropVariety = cropVarietyRepo.getCropVarietyById(dto.getIdGatunek());

        crop.setKlasaGleby(soilClass);
        crop.setRodzajUprawy(cropKind);
        crop.setGatunek(cropVariety);

        List<Cover> covers = new ArrayList<>();
        for (int i : dto.getRyzyka()) {
            Cover cover = coverRepo.getCoverById(i);
            covers.add(cover);
        }
        crop.setOchrony(covers);

        crop.setCzyNasienna(dto.isCzyNasienna());
        crop.setPowierzchnia(dto.getPowierzchnia());
        crop.setWartosc(dto.getWartosc());
        crop.setWartoscRynkowa(crop.getRodzajUprawy().getWartoscUbezpieczenia().wartoscRynkowa() * dto.getPowierzchnia());
        crop.setSumaUbezpieczenia((int) Math.round(dto.getWartosc() * dto.getPowierzchnia()));

        List<Land> existingLands = crop.getDzialki();
        if (existingLands != null) {
            for (Land l : existingLands) {
                landRepo.deleteLandById(l.getIdDzialka());
            }
            crop.getDzialki().clear();
        }

        List<Land> lands = new ArrayList<>();
        for (CalcLandDto cl : dto.getDzialki()) {
            Teryt teryt = terytRepo.getTeryt(cl.getTeryt());

            Land land = new Land();
            land.setUprawa(crop);
            land.setTeryt(teryt);
            land.setObreb(cl.getObreb());
            land.setCzyPoprawna(cl.isCzyPoprawna());
            land.setNumerDzialki(cl.getNumerDzialki());
            land.setKodObrebu(cl.getKodObrebu());

            lands.add(land);
        }


        crop.setDzialki(lands);

        cropRepo.saveCrop(crop);

        return new ResponseEntity<>("Successfully saved data to the Database", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<CalcGetInsurancePeriodDto> findApkQuestionsByCalcId(int calcId) {
        Calculation calculation = calculationRepo.getCalculationById(calcId);
        List<CalcGetApkDto> apk = apkQuestionRepo.findApkQuestionsByCalcId(calcId);

        CalcGetInsurancePeriodDto insurancePeriod = new CalcGetInsurancePeriodDto();

        insurancePeriod.setDataPoczatkuOchrony(calculation.getDataPoczatkuOchrony());
        insurancePeriod.setDataKoncaOchrony(calculation.getDataKoncaOchrony());
        insurancePeriod.setApk(apk);

        if(insurancePeriod.getDataPoczatkuOchrony() == null || insurancePeriod.getDataKoncaOchrony() == null){
            return new ResponseEntity<>(insurancePeriod, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(insurancePeriod, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<CalcGetPersonalDataDto> getPersonalData(int id) {
        CalcGetPersonalDataDto personalData = new CalcGetPersonalDataDto();
        CalcGetPerson policyHolder = calculationRepo.getPolicyHolder(id);
        CalcGetPerson policyInsured = calculationRepo.getPolicyInsured(id);

        personalData.setUbezpieczajacy(policyHolder);
        personalData.setUbezpieczony(policyInsured);

        if(personalData.getUbezpieczony() == null || personalData.getUbezpieczajacy() == null){
            return new ResponseEntity<>(personalData, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personalData, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<List<CalcGetCropDto>> getCrops(Integer id) {

        List<Map<String, Object>> crops = calculationRepo.getCrops(id);
        List<Integer> ryzyka = new ArrayList<>();
        List<CalcLandCropDto> dzialki = new ArrayList<>();
        List<CalcGetCropDto> cropsDto = new ArrayList<>();

        for(Map<String, Object> crop : crops) {
            CalcGetCropDto cropDto = new CalcGetCropDto();
            cropDto.setId((Integer) crop.get("id"));
            cropDto.setIdUprawy((Integer) crop.get("idUprawy"));
            if (crop.get("idGatunek") != null) cropDto.setIdGatunek((Integer) crop.get("idGatunek"));
            cropDto.setIdKlasaGleby((Integer) crop.get("idKlasaGleby"));
            cropDto.setUprawa((String) crop.get("uprawa"));
            cropDto.setGatunek((String) crop.get("gatunek"));
            cropDto.setKlasaGleby((String) crop.get("klasaGleby"));
            cropDto.setCzyNasienna((Boolean) crop.get("czyNasienna"));
            cropDto.setPowierzchnia((Double) crop.get("powierzchnia"));
            cropDto.setWartosc((Double) crop.get("wartosc"));
            cropDto.setWartoscRynkowa((Double) crop.get("wartoscRynkowa"));
            cropDto.setWartoscMax((Double) crop.get("wartoscMax"));

            List<Map<String, Object>> covers = calculationRepo.getCropCovers(id, cropDto.getId());
            for (Map<String, Object> c : covers) {
                ryzyka.add((Integer) c.get("idOchrona"));
            }

            List<Map<String, Object>> lands = calculationRepo.getCropLands(id, cropDto.getId());
            for (Map<String, Object> l : lands) {
                CalcLandCropDto dzialka = new CalcLandCropDto();

                dzialka.setTeryt((String) l.get("teryt"));
                dzialka.setNumerDzialki((String) l.get("numerDzialki"));
                dzialka.setObreb((String) l.get("obreb"));
                dzialka.setKodObrebu((String) l.get("kodObrebu"));
                dzialka.setCzyPoprawna((Boolean) l.get("czyPoprawna"));

                dzialki.add(dzialka);
            }

            cropDto.setDzialki(dzialki);

            cropDto.setRyzyka(ryzyka);

            cropsDto.add(cropDto);
        }

        return new ResponseEntity<>(cropsDto, HttpStatus.OK);

    }

    public ResponseEntity<CalcGetCropDto> getCrop(int calcId, int cropid) {
        Map<String, Object> crop = calculationRepo.getCrop(calcId, cropid);

        List<Integer> ryzyka = new ArrayList<>();
        List<CalcLandCropDto> dzialki = new ArrayList<>();

        CalcGetCropDto cropDto = new CalcGetCropDto();
        cropDto.setId((Integer) crop.get("id"));
        cropDto.setIdUprawy((Integer) crop.get("idUprawy"));
        if (crop.get("idGatunek") != null) cropDto.setIdGatunek((Integer) crop.get("idGatunek"));
        cropDto.setIdKlasaGleby((Integer) crop.get("idKlasaGleby"));
        cropDto.setUprawa((String) crop.get("uprawa"));
        cropDto.setGatunek((String) crop.get("gatunek"));
        cropDto.setKlasaGleby((String) crop.get("klasaGleby"));
        cropDto.setCzyNasienna((Boolean) crop.get("czyNasienna"));
        cropDto.setPowierzchnia((Double) crop.get("powierzchnia"));
        cropDto.setWartosc((Double) crop.get("wartosc"));
        cropDto.setWartoscRynkowa((Double) crop.get("wartoscRynkowa"));
        cropDto.setWartoscMax((Double) crop.get("wartoscMax"));

        List<Map<String, Object>> covers = calculationRepo.getCropCovers(calcId, cropid);
        for (Map<String, Object> c : covers) {
            ryzyka.add((Integer) c.get("idOchrona"));
        }

        List<Map<String, Object>> lands = calculationRepo.getCropLands(calcId, cropid);
        for (Map<String, Object> l : lands) {
            CalcLandCropDto dzialka = new CalcLandCropDto();

            dzialka.setTeryt((String) l.get("teryt"));
            dzialka.setNumerDzialki((String) l.get("numerDzialki"));
            dzialka.setObreb((String) l.get("obreb"));
            dzialka.setKodObrebu((String) l.get("kodObrebu"));
            dzialka.setCzyPoprawna((Boolean) l.get("czyPoprawna"));

            dzialki.add(dzialka);
        }

        cropDto.setDzialki(dzialki);

        cropDto.setRyzyka(ryzyka);

        return new ResponseEntity<>(cropDto, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<String> deleteCrop(int calcId, int cropid) {
        Calculation calculation = calculationRepo.getCalculationById(calcId);
        Crop cropForRemove = null;

        for(Crop c : calculation.getUprawy()){
            if(c.getIdUprawa() == cropid) {
                cropForRemove = c;
                break;
            }
        }

        if(cropForRemove != null) {
            calculation.getUprawy().remove(cropForRemove);
            cropRepo.removeCrop(cropForRemove.getIdUprawa());
        }


        return new ResponseEntity<>("Removed", HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<String> addLivestock(CalcLivestockDto livestockDto, int calcId) {
        Calculation calculation = calculationRepo.getCalculationById(calcId);
        LivestockKind livestockKind = livestockKindRepo.getLivestockById(livestockDto.getIdRodzajZwierzecia());
        Livestock livestock = new Livestock();

        livestock.setRodzajZwierzecia(livestockKind);
        livestock.setLiczba(livestockDto.getLiczba());
        livestock.setNaMieso(livestockDto.isNaMieso());
        livestock.setSumaUbezpieczenia((int) (livestockDto.getLiczba() * livestockDto.getWartosc()));
        livestock.setWartosc(livestockDto.getWartosc());
        livestock.setWartoscRynkowa(livestockKind.getWartoscUbezpieczenia().wartoscRynkowa());

        for(int i: livestockDto.getRyzyka()){
            Cover cover = coverRepo.getCoverById(i);
            livestock.getOchrony().add(cover);
        }

        livestock.setKalkulacja(calculation);
        calculation.getZwierzeta().add(livestock);

        calculationRepo.saveCalculation(calculation);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<CalcLivestockDto>> getLivestock(int calcId) {
        Calculation calculation = calculationRepo.getCalculationById(calcId);
        List<CalcLivestockDto> calculationDtos = new ArrayList<>();

        for(Livestock l: calculation.getZwierzeta()){
            CalcLivestockDto livestockDto = new CalcLivestockDto();
            List<Integer> livestockCovers = new ArrayList<>();

            livestockDto.setId(l.getIdZwierze());
            livestockDto.setIdRodzajZwierzecia(l.getRodzajZwierzecia().getIdRodzajZwierzecia());
            livestockDto.setLiczba(l.getLiczba());
            livestockDto.setNaMieso(l.isNaMieso());
            livestockDto.setWartosc(l.getWartosc());
            livestockDto.setSumaUbezpieczenia(l.getSumaUbezpieczenia());
            livestockDto.setWartoscRynkowa(l.getWartoscRynkowa());
            livestockDto.setNazwaZwierzecia(l.getRodzajZwierzecia().getNazwa().nazwa());

            for(Cover c: l.getOchrony()){
                livestockCovers.add(c.getIdOchrona());
            }

            livestockDto.setRyzyka(livestockCovers);

            calculationDtos.add(livestockDto);
        }

        return new ResponseEntity<>(calculationDtos, HttpStatus.OK);

    }

    public ResponseEntity<String> deleteLivestock(int livestockId) {
        livestockRepo.deleteLivestock(livestockId);

        return new ResponseEntity<>("successfully removed from the database", HttpStatus.OK);
    }

    public ResponseEntity<CalcLivestockDto> getLivestockById(int livestockId) {
        Livestock livestock = livestockRepo.findLivestockById(livestockId);
        CalcLivestockDto livestockDto = new CalcLivestockDto();

        List<Integer> livestockCovers = new ArrayList<>();

        livestockDto.setId(livestock.getIdZwierze());
        livestockDto.setIdRodzajZwierzecia(livestock.getRodzajZwierzecia().getIdRodzajZwierzecia());
        livestockDto.setLiczba(livestock.getLiczba());
        livestockDto.setNaMieso(livestock.isNaMieso());
        livestockDto.setWartosc(livestock.getWartosc());
        livestockDto.setSumaUbezpieczenia(livestock.getSumaUbezpieczenia());
        livestockDto.setWartoscRynkowa(livestock.getWartoscRynkowa());
        livestockDto.setNazwaZwierzecia(livestock.getRodzajZwierzecia().getNazwa().nazwa());

        for(Cover c: livestock.getOchrony()){
            livestockCovers.add(c.getIdOchrona());
        }

        livestockDto.setRyzyka(livestockCovers);

        return new ResponseEntity<>(livestockDto, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<String> modifyLivestock(CalcLivestockDto livestockDto ,int livestockId) {
        Livestock livestock = livestockRepo.findLivestockById(livestockId);
        LivestockKind livestockKind = livestockKindRepo.getLivestockById(livestockDto.getIdRodzajZwierzecia());
        List<Cover> covers = new ArrayList<>();

        livestock.setRodzajZwierzecia(livestockKind);
        livestock.setLiczba(livestockDto.getLiczba());
        livestock.setNaMieso(livestockDto.isNaMieso());
        livestock.setSumaUbezpieczenia((int) (livestockDto.getLiczba() * livestockDto.getWartosc()));
        livestock.setWartosc(livestockDto.getWartosc());
        livestock.setWartoscRynkowa(livestockKind.getWartoscUbezpieczenia().wartoscRynkowa());

        for(int i: livestockDto.getRyzyka()){
            Cover cover = coverRepo.getCoverById(i);
//            livestock.getOchrony().add(cover);
            covers.add(cover);
        }

        livestock.setOchrony(covers);

        livestockRepo.saveLivestock(livestock);

        return new ResponseEntity<>("successfully modified", HttpStatus.OK);
    }
}
