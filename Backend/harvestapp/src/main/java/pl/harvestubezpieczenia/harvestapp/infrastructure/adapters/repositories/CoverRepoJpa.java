package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;

@Repository
public interface CoverRepoJpa extends GenericCrudRepoJpa<Cover> {
}
