package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class CoverService extends GenericService<Cover, CoverDto> {
    public CoverService(Map<String, GenericCrudRepo<Cover>> genericCrudRepos, Map<String, GenericMapper<Cover, CoverDto>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }
}
