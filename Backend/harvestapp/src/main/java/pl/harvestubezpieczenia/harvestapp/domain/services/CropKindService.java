package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class CropKindService extends GenericService<CropKind, CropKindDTO>{

    public CropKindService(GenericCrudRepo<CropKind> genericCrudRepo, GenericMapper<CropKind, CropKindDTO> genericMapper) {
        super(genericCrudRepo, genericMapper);
    }

}
