package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class SoilClassService extends GenericService<SoilClass, SoilClassDto> {

    @Autowired
    public SoilClassService(GenericCrudRepo<SoilClass> repo, GenericMapper<SoilClass, SoilClassDto> map) {
        super(repo, map);
    }
}
