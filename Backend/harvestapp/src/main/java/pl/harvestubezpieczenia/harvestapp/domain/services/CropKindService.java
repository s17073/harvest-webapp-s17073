package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class CropKindService extends GenericService<CropKind>{

    public CropKindService(GenericCrudRepo<CropKind> genericCrudRepo) {
        super(genericCrudRepo);
    }

}
