package pl.harvestubezpieczenia.harvestapp.domain.Mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CoverName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuranceClass;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Component
@Qualifier("coverMapper")
public class CoverMapper implements GenericMapper<Cover, CoverDto> {


    @Override
    public Cover mapToEntity(CoverDto dto) {
        Cover entity = new Cover();

        entity.setNazwa(new CoverName(dto.getNazwa()));
        entity.setGrupaMinisterialna(new InsuranceClass(dto.getGrupaMinisterialna()));
        entity.setTaryfa(new Season(dto.getTaryfa()));
        entity.setOpis(dto.getOpis());
        entity.setCzyUprawa(dto.isCzyUprawa());
        entity.setCzyZwierze(dto.isCzyZwierze());
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public CoverDto mapToDto(Cover entity) {
        CoverDto dto = new CoverDto();

        dto.setNazwa(entity.getNazwa().nazwa());
        dto.setGrupaMinisterialna(entity.getGrupaMinisterialna().grupaMinisterialna());
        dto.setTaryfa(entity.getTaryfa().taryfa());
        dto.setOpis(entity.getOpis());
        dto.setCzyUprawa(entity.isCzyUprawa());
        dto.setCzyZwierze(entity.isCzyZwierze());
        dto.setCzyAktywna(entity.isCzyAktywna());

        return dto;
    }
}
