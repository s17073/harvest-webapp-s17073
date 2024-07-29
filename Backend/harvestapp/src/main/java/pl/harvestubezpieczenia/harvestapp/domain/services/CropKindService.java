package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class CropKindService extends GenericService<CropKind, CropKindDto>{
    public CropKindService(Map<String, GenericCrudRepo<CropKind>> genericCrudRepos, Map<String, GenericMapper<CropKind, CropKindDto>> genericMappers) {
        super(genericCrudRepos, genericMappers);
    }

}
