package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.stereotype.Component;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.LandRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.Land;
import pl.harvestubezpieczenia.harvestapp.domain.ports.LandRepo;

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
