package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropKindRepo;

@Repository
@Qualifier("cropKindRepoJpa")
public interface CropKindRepoJpa extends GenericCrudRepoJpa<CropKind, Long>, CropKindRepo {


}
