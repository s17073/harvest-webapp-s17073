package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class LivestockKindAdapterJpa extends GenericCrudRepoAdapterJpa<LivestockKind>{
    public LivestockKindAdapterJpa(@Qualifier("livestockKindRepoJpa") GenericCrudRepoJpa<LivestockKind> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
