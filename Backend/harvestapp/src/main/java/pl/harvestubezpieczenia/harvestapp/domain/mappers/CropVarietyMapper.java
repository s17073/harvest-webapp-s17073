package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropVarietyName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

@Component
@Qualifier("cropVarietyMapper")
public class CropVarietyMapper implements GenericMapper<CropVariety, CropVarietyDto> {
    @Override
    public CropVariety mapToEntity(CropVarietyDto dto) {
        CropVariety entity = new CropVariety();

        entity.setNazwaGatunku(new CropVarietyName(dto.getNazwaGatunku()));
        entity.setCzyAktywna(dto.isCzyAktywna());
        entity.setDataModyfikacji(new ModificationDate());

        return entity;
    }

    @Override
    public CropVarietyDto mapToDto(CropVariety entity) {
        CropVarietyDto dto = new CropVarietyDto();

        dto.setNazwaGatunku(entity.getNazwaGatunku().nazwaGatunku());
        dto.setCzyAktywna(entity.isCzyAktywna());

        if (!entity.getCropKindVarieties().isEmpty()) {
            for (CropKindVariety ckv : entity.getCropKindVarieties()) {
                dto.getUprawy().add(ckv.getCropKind().getName());
            }
        }

        return dto;
    }
}
