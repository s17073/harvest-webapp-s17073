package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.ports.ApkQuestionRepo;

@Repository
@Qualifier("apkQuestionRepoJpa")
public interface ApkQuestionRepoJpa extends GenericCrudRepoJpa<ApkQuestion, Long>, ApkQuestionRepo {
}
