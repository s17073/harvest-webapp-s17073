package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.Land;

public interface LandRepo {
    void saveLand(Land land);

    void deleteLandById(int idDzialka);
}
