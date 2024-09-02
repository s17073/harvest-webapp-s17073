package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.Livestock;

public interface LivestockRepo {

    void deleteLivestock(int livestockId);

    Livestock findLivestockById(int livestockId);

    void saveLivestock(Livestock livestock);
}
