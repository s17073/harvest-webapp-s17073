package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropKindName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Component
@Qualifier("cropKindMapper")
public class CropKindMapper implements GenericMapper<CropKind, CropKindDto> {

    @Override
    public CropKind mapToEntity(CropKindDto dto) {
        CropKind entity = new CropKind();

        entity.setNazwaUprawy(new CropKindName(dto.getNazwaUprawy()));
        entity.setTaryfa(new Season(dto.getTaryfa()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setWartoscUbezpieczenia(new InsuredValue(dto.getWartoscRynkowa()));
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public CropKindDto mapToDto(CropKind entity) {
        CropKindDto dto = new CropKindDto();

        dto.setId(entity.getId());
        dto.setNazwaUprawy(entity.getNazwaUprawy().nazwaUprawy());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setCzyAktywna(entity.isCzyAktywna());
        dto.setWartoscRynkowa(entity.getWartoscUbezpieczenia().wartoscRynkowa());
        dto.setWartoscMax(entity.getWartoscUbezpieczenia().wartoscMax());

        if(!entity.getCropKindVarieties().isEmpty()) {
            for (CropKindVariety ckv : entity.getCropKindVarieties()) {
                dto.getGatunki().add(ckv.getCropVariety().getName());
            }
        }
        return dto;
    }

}
