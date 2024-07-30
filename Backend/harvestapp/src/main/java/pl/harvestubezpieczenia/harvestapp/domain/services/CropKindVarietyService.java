package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class CropKindVarietyService extends  GenericService<CropKindVariety, CropKindVarietyDto> {

    @Autowired
    public CropKindVarietyService(GenericCrudRepo<CropKindVariety> repo, GenericMapper<CropKindVariety, CropKindVarietyDto> map) {
        super(repo, map);
    }
}
