package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Name;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Component
public class CropKindMapper implements GenericMapper<CropKind, CropKindDTO> {

    @Override
    public CropKind mapToEntity(CropKindDTO cropKindDTO) {
        CropKind cropKind = new CropKind();
        cropKind.setNazwaUprawy(new Name(cropKindDTO.getNazwaUprawy()));
        cropKind.setTaryfa(new Season(cropKindDTO.getTaryfa()));
        cropKind.setCzyAktywna(cropKindDTO.isCzyAktywna());
        cropKind.setWartoscUbezpieczenia(new InsuredValue(cropKindDTO.getWartoscRynkowa()));
        cropKind.setDataModyfikacji(new ModificationDate());

        return cropKind;
    }

    @Override
    public CropKindDTO mapToDto(CropKind cropKind) {
        CropKindDTO cropKindDto = new CropKindDTO();
        cropKindDto.setNazwaUprawy(cropKind.getNazwaUprawy().nazwaUprawy());
        cropKindDto.setTaryfa(cropKind.getTaryfa().taryfa());
        cropKindDto.setCzyAktywna(cropKind.isCzyAktywna());
        cropKindDto.setWartoscRynkowa(cropKind.getWartoscUbezpieczenia().wartoscRynkowa());
        cropKindDto.setWartoscMax(cropKind.getWartoscUbezpieczenia().wartoscMax());

        return cropKindDto;
    }

}
