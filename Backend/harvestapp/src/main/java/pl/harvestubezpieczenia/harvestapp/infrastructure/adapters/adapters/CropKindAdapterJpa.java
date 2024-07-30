package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class CropKindAdapterJpa extends GenericCrudRepoAdapterJpa<CropKind> {

    public CropKindAdapterJpa(@Qualifier("cropKindRepoJpa") GenericCrudRepoJpa<CropKind> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }

}