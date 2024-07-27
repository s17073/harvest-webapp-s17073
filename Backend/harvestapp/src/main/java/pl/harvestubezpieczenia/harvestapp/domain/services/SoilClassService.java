package pl.harvestubezpieczenia.harvestapp.domain.services;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDTO;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

public class SoilClassService extends GenericService<SoilClass, SoilClassDTO> {

    public SoilClassService(Map<String, GenericCrudRepo<SoilClass>> genericCrudRepos, Map<String, GenericMapper<SoilClass, SoilClassDTO>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }
}
