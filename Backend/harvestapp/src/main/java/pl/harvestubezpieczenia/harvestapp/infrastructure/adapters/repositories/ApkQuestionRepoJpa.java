package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;

import java.util.List;

@Repository
public interface ApkQuestionRepoJpa extends GenericCrudRepoJpa<ApkQuestion> {

    @Query(value = "select * from harvest.apk where czy_aktywna = true and data_usuniecia is null", nativeQuery = true)
    List<ApkQuestion> getApkQuestions();

}
