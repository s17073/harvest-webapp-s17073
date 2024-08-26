package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;

import java.util.List;

public interface CropVarietyRepo extends  GenericCrudRepo<CropVariety>{
    List<CropVarietyListDto> getCropVarietyNames(int cropId);
}
