package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;

public interface CropKindRepo extends GenericCrudRepo<CropKind> {

    CropKind getCropKindById(int idRodzajUprawy);
}
