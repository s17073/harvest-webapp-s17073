package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropKindName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Component
@Qualifier("cropKindMapper")
public class CropKindMapper implements GenericMapper<CropKind, CropKindDto> {

    private final CropVarietyMapper mapper = new CropVarietyMapper();

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

        dto.setNazwaUprawy(entity.getNazwaUprawy().nazwaUprawy());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setCzyAktywna(entity.isCzyAktywna());
        dto.setWartoscRynkowa(entity.getWartoscUbezpieczenia().wartoscRynkowa());
        dto.setWartoscMax(entity.getWartoscUbezpieczenia().wartoscMax());

        System.out.println(entity.getCropKindVarieties());

        for(CropKindVariety ckv : entity.getCropKindVarieties()){

            System.out.println(mapper.mapToDto(ckv.getCropVariety()));
//            dto.getGatunki().add(mapper.mapToDto(ckv.getCropVariety()));

        }

        return dto;
    }

}
