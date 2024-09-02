package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.Crop;

public interface CropRepo {
    void saveCrop(Crop crop);

    Crop getCropById(int idCrop);

    void removeCrop(int idUprawa);

    void removeCropLandAndCovers(int idCrop);
}
