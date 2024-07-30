package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;

@Repository
public interface LivestockKindRepoJpa extends GenericCrudRepoJpa<LivestockKind> {

}
