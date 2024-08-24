package pl.harvestubezpieczenia.harvestapp.domain.mappers;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;

@Component
public class CropKindListMapper {

    public CropKindListDto mapToDto(CropKind entity){
        CropKindListDto dto = new CropKindListDto();

        dto.setIdUprawy(entity.getId());
        dto.setNazwaUprawy(entity.getNazwaUprawy().nazwaUprawy());

        return dto;
    }

}
