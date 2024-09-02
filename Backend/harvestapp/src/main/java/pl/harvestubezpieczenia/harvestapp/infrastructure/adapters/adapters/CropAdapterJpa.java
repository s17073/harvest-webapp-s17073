package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.model.Crop;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.CropRepoJpa;

@Component
public class CropAdapterJpa implements CropRepo {
    private final CropRepoJpa cropRepoJpa;

    public CropAdapterJpa(CropRepoJpa cropRepoJpa) {
        this.cropRepoJpa = cropRepoJpa;
    }

    @Override
    public void saveCrop(Crop crop) {
        cropRepoJpa.save(crop);
    }

    @Override
    public Crop getCropById(int idCrop) {
        return cropRepoJpa.getCropById(idCrop);
    }

    @Override
    @Transactional
    public void removeCrop(int idUprawa) {
        cropRepoJpa.removeLandByCropId(idUprawa);
        cropRepoJpa.removeCoverByCropId(idUprawa);
        cropRepoJpa.removeCropById(idUprawa);
    }

    @Override
    @Transactional
    public void removeCropLandAndCovers(int idUprawa) {
        cropRepoJpa.removeLandByCropId(idUprawa);
        cropRepoJpa.removeCoverByCropId(idUprawa);
    }
}
