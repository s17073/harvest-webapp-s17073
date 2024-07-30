package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;

@Repository
public interface CropKindVarietyRepoJpa extends GenericCrudRepoJpa<CropKindVariety> {
}
