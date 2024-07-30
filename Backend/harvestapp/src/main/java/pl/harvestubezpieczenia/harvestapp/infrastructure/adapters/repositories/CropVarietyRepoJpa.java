package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;

@Repository
public interface CropVarietyRepoJpa extends GenericCrudRepoJpa<CropVariety> {
}
