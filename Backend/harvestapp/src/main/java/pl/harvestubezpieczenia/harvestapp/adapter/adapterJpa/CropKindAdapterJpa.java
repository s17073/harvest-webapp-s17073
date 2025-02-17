package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.CropKindRepoJpa;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropKindRepo;

@Service
public class CropKindAdapterJpa extends GenericCrudRepoAdapterJpa<CropKind> implements CropKindRepo {
    private final CropKindRepoJpa cropKindRepoJpa;

    public CropKindAdapterJpa(@Qualifier("cropKindRepoJpa") GenericCrudRepoJpa<CropKind> genericCrudRepoJpa, CropKindRepoJpa cropKindRepoJpa) {
        super(genericCrudRepoJpa);
        this.cropKindRepoJpa = cropKindRepoJpa;
    }

    public CropKind getCropKindById(int id) {
        return cropKindRepoJpa.getCropKindById(id);
    }
}