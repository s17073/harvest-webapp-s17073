package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class CropKindVarietyAdapterJpa extends GenericCrudRepoAdapterJpa<CropKindVariety>{
    public CropKindVarietyAdapterJpa(@Qualifier("cropKindVarietyRepoJpa") GenericCrudRepoJpa<CropKindVariety> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
