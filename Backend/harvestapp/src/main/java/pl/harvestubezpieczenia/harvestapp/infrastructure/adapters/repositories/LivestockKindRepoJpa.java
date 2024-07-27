package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.LivestockKindRepo;

@Repository
@Qualifier("livestockKindRepoJpa")
public interface LivestockKindRepoJpa extends GenericCrudRepoJpa<LivestockKind, Long>, LivestockKindRepo {

}
