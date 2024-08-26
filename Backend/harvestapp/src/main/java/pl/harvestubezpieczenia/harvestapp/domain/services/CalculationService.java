package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcApk;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcInsurancePeriodDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkCalculation;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;
import pl.harvestubezpieczenia.harvestapp.domain.ports.ApkCalculationRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.ApkQuestionRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CalculationRepo;

@Service
public class CalculationService {

    private final CalculationRepo calculationRepo;
    private final ApkQuestionRepo apkQuestionRepo;
    private final ApkCalculationRepo apkCalculationRepo;

    public CalculationService(CalculationRepo calculationRepo, ApkQuestionRepo apkQuestionRepo, ApkCalculationRepo apkCalculationRepo) {
        this.calculationRepo = calculationRepo;
        this.apkQuestionRepo = apkQuestionRepo;
        this.apkCalculationRepo = apkCalculationRepo;
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

            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
