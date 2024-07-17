package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropKindRepo;

@Repository
public interface CropKindRepoJpa extends CrudRepoJpa<CropKind, Long>, CropKindRepo {


}
