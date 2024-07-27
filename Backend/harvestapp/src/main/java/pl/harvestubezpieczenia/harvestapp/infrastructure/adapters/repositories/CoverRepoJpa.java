package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CoverRepo;

@Repository
@Qualifier("coverRepoJpa")
public interface CoverRepoJpa extends GenericCrudRepoJpa<Cover, Long>, CoverRepo {
}
