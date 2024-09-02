package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.domain.model.Land;
import pl.harvestubezpieczenia.harvestapp.domain.ports.LandRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.LandRepoJpa;

@Component
public class LandRepoAdapterJpa implements LandRepo {

    private final LandRepoJpa landRepoJpa;

    public LandRepoAdapterJpa(LandRepoJpa landRepoJpa) {
        this.landRepoJpa = landRepoJpa;
    }

    @Override
    public void saveLand(Land land) {
        landRepoJpa.save(land);
    }

    @Override
    public void deleteLandById(int idDzialka) {
        landRepoJpa.deleteLandById(idDzialka);
    }
}
