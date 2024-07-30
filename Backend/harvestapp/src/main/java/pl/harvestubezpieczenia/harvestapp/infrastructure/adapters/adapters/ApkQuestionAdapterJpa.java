package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class ApkQuestionAdapterJpa extends GenericCrudRepoAdapterJpa<ApkQuestion>{
    public ApkQuestionAdapterJpa(@Qualifier("apkQuestionRepoJpa") GenericCrudRepoJpa<ApkQuestion> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
