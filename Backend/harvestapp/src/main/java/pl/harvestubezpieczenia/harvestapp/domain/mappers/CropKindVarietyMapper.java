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
        return null;
    }

    @Transactional
    @Override
    public CropKindVarietyDto mapToDto(CropKindVariety entity) {
        CropKindVarietyDto dto = new CropKindVarietyDto();

        dto.setId(entity.getIdUprawaGatunek());
        dto.setUprawa(entity.getCropKind().getName());
        dto.setGatunek(entity.getCropVariety().getName());

        return dto;
    }
}
