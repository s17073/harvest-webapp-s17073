package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class CropVarietyAdapterJpa extends GenericCrudRepoAdapterJpa<CropVariety> {
    public CropVarietyAdapterJpa(@Qualifier("cropVarietyRepoJpa") GenericCrudRepoJpa<CropVariety> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
