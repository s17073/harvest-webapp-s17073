package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;

@Repository
public interface CropKindRepoJpa extends GenericCrudRepoJpa<CropKind> {

}
