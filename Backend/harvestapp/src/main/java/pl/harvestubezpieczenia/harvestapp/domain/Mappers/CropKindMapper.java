package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropKindName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Component
@Qualifier("cropKindMapper")
public class CropKindMapper implements GenericMapper<CropKind, CropKindDTO> {

    @Override
    public CropKind mapToEntity(CropKindDTO dto) {
        CropKind entity = new CropKind();
        entity.setNazwaUprawy(new CropKindName(dto.getNazwaUprawy()));
        entity.setTaryfa(new Season(dto.getTaryfa()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setWartoscUbezpieczenia(new InsuredValue(dto.getWartoscRynkowa()));
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public CropKindDTO mapToDto(CropKind entity) {
        CropKindDTO dto = new CropKindDTO();

        dto.setNazwaUprawy(entity.getNazwaUprawy().nazwaUprawy());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setCzyAktywna(entity.isCzyAktywna());
        dto.setWartoscRynkowa(entity.getWartoscUbezpieczenia().wartoscRynkowa());
        dto.setWartoscMax(entity.getWartoscUbezpieczenia().wartoscMax());

        return dto;
    }

}
