package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class CropKindVarietyService extends  GenericService<CropKindVariety, CropKindVarietyDto> {
    public CropKindVarietyService(Map<String, GenericCrudRepo<CropKindVariety>> genericCrudRepos, Map<String, GenericMapper<CropKindVariety, CropKindVarietyDto>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }
}
