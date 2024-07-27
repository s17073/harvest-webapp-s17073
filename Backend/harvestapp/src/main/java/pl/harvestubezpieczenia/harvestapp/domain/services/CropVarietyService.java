package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class CropVarietyService extends GenericService<CropVariety, CropVarietyDto> {
    public CropVarietyService(Map<String, GenericCrudRepo<CropVariety>> genericCrudRepos, Map<String, GenericMapper<CropVariety, CropVarietyDto>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }
}
