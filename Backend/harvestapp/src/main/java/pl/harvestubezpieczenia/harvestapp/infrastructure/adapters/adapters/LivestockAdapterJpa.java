package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.model.Livestock;
import pl.harvestubezpieczenia.harvestapp.domain.ports.LivestockRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.LivestockRepoJpa;

@Service
public class LivestockAdapterJpa implements LivestockRepo {

    private final LivestockRepoJpa livestockRepoJpa;

    public LivestockAdapterJpa(LivestockRepoJpa livestockRepoJpa) {
        this.livestockRepoJpa = livestockRepoJpa;
    }

    @Override
    @Transactional
    public void deleteLivestock(int livestockId) {
        livestockRepoJpa.removeCoversByLivestockId(livestockId);
        livestockRepoJpa.removeLivestockById(livestockId);
    }

    @Override
    public Livestock findLivestockById(int livestockId) {
        return livestockRepoJpa.findLivestockById(livestockId);
    }

    @Override
    public void saveLivestock(Livestock livestock) {
        livestockRepoJpa.save(livestock);
    }


}
