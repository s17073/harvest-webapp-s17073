package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class CropVarietyService extends GenericService<CropVariety, CropVarietyDto> {

    @Autowired
    public CropVarietyService(GenericCrudRepo<CropVariety> repo, GenericMapper<CropVariety, CropVarietyDto> map) {
        super(repo, map);
    }
}
