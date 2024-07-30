package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class CoverService extends GenericService<Cover, CoverDto> {

    @Autowired
    public CoverService(GenericCrudRepo<Cover> repo, GenericMapper<Cover, CoverDto> map) {
        super(repo, map);
    }
}
