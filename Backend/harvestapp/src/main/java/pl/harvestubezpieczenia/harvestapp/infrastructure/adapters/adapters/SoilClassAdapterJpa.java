package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class SoilClassAdapterJpa extends GenericCrudRepoAdapterJpa<SoilClass>{

    public SoilClassAdapterJpa(@Qualifier("soilClassRepoJpa") GenericCrudRepoJpa<SoilClass> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
