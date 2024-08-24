package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;

@Component
@Qualifier("cropKindVarietyMapper")
public class CropKindVarietyMapper implements GenericMapper<CropKindVariety, CropKindVarietyDto> {
    @Override
    public CropKindVariety mapToEntity(CropKindVarietyDto dto) {
        CropKindVariety entity = new CropKindVariety();

        entity.setCropKind(dto.getUprawa());
        entity.setCropVariety(dto.getGatunek());

        return entity;
    }

    @Transactional
    @Override
    public CropKindVarietyDto mapToDto(CropKindVariety entity) {
        CropKindVarietyDto dto = new CropKindVarietyDto();

        dto.setId(entity.getId());
        dto.setCzyAktywna(entity.getCropVariety().isCzyAktywna());
        dto.setTaryfa(entity.getCropKind().getTaryfa().taryfa());
        dto.setIdGatunek(entity.getIdUprawaGatunek());
        dto.setNazwaUprawy(entity.getCropKind().getName());
        dto.setNazwaGatunku(entity.getCropVariety().getName());
        dto.setIdUprawa(entity.getCropKind().getId());

        return dto;
    }

}
