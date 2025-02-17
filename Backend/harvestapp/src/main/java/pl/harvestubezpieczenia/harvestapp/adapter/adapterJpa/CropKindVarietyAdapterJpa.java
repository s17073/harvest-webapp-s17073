package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;

@Service
public class CropKindVarietyAdapterJpa extends GenericCrudRepoAdapterJpa<CropKindVariety>{
    public CropKindVarietyAdapterJpa(@Qualifier("cropKindVarietyRepoJpa") GenericCrudRepoJpa<CropKindVariety> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
