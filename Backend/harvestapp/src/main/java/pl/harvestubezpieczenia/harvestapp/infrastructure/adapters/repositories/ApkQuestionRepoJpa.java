package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;

@Repository
public interface ApkQuestionRepoJpa extends GenericCrudRepoJpa<ApkQuestion> {
}
