package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.SoilClassName;

@Component
@Qualifier("soilClassMapper")
public class SoilClassMapper implements GenericMapper<SoilClass, SoilClassDto> {


    @Override
    public SoilClass mapToEntity(SoilClassDto dto) {
        SoilClass entity = new SoilClass();

        entity.setKlasaGleby(new SoilClassName(dto.getKlasaGleby()));
        entity.setOpis(dto.getOpis());
        entity.setTaryfa(new Season(dto.getTaryfa()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public SoilClassDto mapToDto(SoilClass entity) {
        SoilClassDto dto = new SoilClassDto();

        dto.setId(entity.getId());
        dto.setKlasaGleby(entity.getKlasaGleby().klasaGleby());
        dto.setOpis(entity.getOpis());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setCzyAktywna(entity.isCzyAktywna());

        return dto;
    }
}
