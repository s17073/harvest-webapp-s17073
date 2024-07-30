package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class CoverAdapterJpa extends GenericCrudRepoAdapterJpa<Cover> {

    public CoverAdapterJpa(@Qualifier("coverRepoJpa") GenericCrudRepoJpa<Cover> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }

}
