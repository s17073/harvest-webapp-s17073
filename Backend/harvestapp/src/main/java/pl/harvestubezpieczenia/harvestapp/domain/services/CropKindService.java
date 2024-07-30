package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class CropKindService extends GenericService<CropKind, CropKindDto>{

    @Autowired
    public CropKindService(GenericCrudRepo<CropKind> repo, GenericMapper<CropKind, CropKindDto> map) {
        super(repo, map);
    }
}
