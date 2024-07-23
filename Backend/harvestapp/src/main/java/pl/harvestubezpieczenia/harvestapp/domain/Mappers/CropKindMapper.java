package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

@Component
public class CropKindMapper implements GenericMapper<CropKind, CropKindDTO> {

    @Override
    public CropKind mapToEntity(CropKindDTO cropKindDTO) {
        CropKind cropKind = new CropKind();
        cropKind.setNazwaUprawy(cropKindDTO.getNazwaUprawy());
        cropKind.setTaryfa(cropKindDTO.getTaryfa());
        cropKind.setCzyAktywna(cropKindDTO.isCzyAktywna());
        cropKind.setWartoscUbezpieczenia(new InsuredValue(cropKindDTO.getWartoscRynkowa()));
        cropKind.setDataModyfikacji(new ModificationDate());

        return cropKind;
    }

}
