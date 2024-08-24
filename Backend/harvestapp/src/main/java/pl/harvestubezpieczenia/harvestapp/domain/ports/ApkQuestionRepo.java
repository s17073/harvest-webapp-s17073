package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;

import java.util.List;

public interface ApkQuestionRepo extends  GenericCrudRepo<ApkQuestion>{

    List<ApkQuestion> getApkQuestions();

}
