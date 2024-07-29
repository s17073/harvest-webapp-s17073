package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropKindVarietyRepo;

@Repository
@Qualifier("cropKindVarietyRepoJpa")
public interface CropKindVarietyRepoJpa extends GenericCrudRepoJpa<CropKindVariety, Long>, CropKindVarietyRepo {
}
