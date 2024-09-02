package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropVarietyRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.CropVarietyRepoJpa;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

import java.util.List;

@Service
public class CropVarietyAdapterJpa extends GenericCrudRepoAdapterJpa<CropVariety> implements CropVarietyRepo {
    private final CropVarietyRepoJpa cropVarietyRepoJpa;

    public CropVarietyAdapterJpa(@Qualifier("cropVarietyRepoJpa") GenericCrudRepoJpa<CropVariety> genericCrudRepoJpa, CropVarietyRepoJpa cropVarietyRepoJpa) {
        super(genericCrudRepoJpa);
        this.cropVarietyRepoJpa = cropVarietyRepoJpa;
    }

    @Override
    public List<CropVarietyListDto> getCropVarietyNames(int cropId) {
        return cropVarietyRepoJpa.getCropVarietyNames(cropId);
    }

    @Override
    public CropVariety getCropVarietyById(int idGatunek) {
        return cropVarietyRepoJpa.getCropVarietyById(idGatunek);
    }
}
