package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.SoilClassName;

@Component
@Qualifier("soilClassMapper")
public class SoilClassMapper implements GenericMapper<SoilClass, SoilClassDTO> {


    @Override
    public SoilClass mapToEntity(SoilClassDTO dto) {
        SoilClass entity = new SoilClass();

        entity.setKlasaGleby(new SoilClassName(dto.getKlasaGleby()));
        entity.setOpis(dto.getOpis());
        entity.setTaryfa(new Season(dto.getTaryfa()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public SoilClassDTO mapToDto(SoilClass entity) {
        SoilClassDTO dto = new SoilClassDTO();

        dto.setKlasaGleby(entity.getKlasaGleby().klasaGleby());
        dto.setOpis(entity.getOpis());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setCzyAktywna(entity.isCzyAktywna());

        return dto;
    }
}
