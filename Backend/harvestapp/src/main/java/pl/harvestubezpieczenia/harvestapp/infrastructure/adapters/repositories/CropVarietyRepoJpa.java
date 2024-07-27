package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropVarietyRepo;

@Repository
@Qualifier("cropVarietyRepoJpa")
public interface CropVarietyRepoJpa extends GenericCrudRepoJpa<CropVariety, Long>, CropVarietyRepo {
}
