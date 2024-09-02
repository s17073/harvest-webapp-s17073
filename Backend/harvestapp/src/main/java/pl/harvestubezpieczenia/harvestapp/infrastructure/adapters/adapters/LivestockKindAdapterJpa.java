package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.LivestockKindRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.LivestockKindRepoJpa;

import java.util.List;

@Service
public class LivestockKindAdapterJpa extends GenericCrudRepoAdapterJpa<LivestockKind> implements LivestockKindRepo{

    private final LivestockKindRepoJpa livestockKindRepoJpa;

    public LivestockKindAdapterJpa(@Qualifier("livestockKindRepoJpa") GenericCrudRepoJpa<LivestockKind> genericCrudRepoJpa, LivestockKindRepoJpa livestockKindRepoJpa) {
        super(genericCrudRepoJpa);
        this.livestockKindRepoJpa = livestockKindRepoJpa;
    }

    @Override
    public List<LivestockKind> getLivestockList() {
        return livestockKindRepoJpa.getLivestockList();
    }

    @Override
    public LivestockKind getLivestockById(int idRodzajZwierzecia) {
        return livestockKindRepoJpa.getLivestockById(idRodzajZwierzecia);
    }
}
