package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcGetApkDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.ports.ApkQuestionRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.ApkQuestionRepoJpa;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

import java.util.List;

@Service
public class ApkQuestionAdapterJpa extends GenericCrudRepoAdapterJpa<ApkQuestion> implements ApkQuestionRepo {
    private final ApkQuestionRepoJpa apkQuestionRepoJpa;

    public ApkQuestionAdapterJpa(@Qualifier("apkQuestionRepoJpa") GenericCrudRepoJpa<ApkQuestion> genericCrudRepoJpa, ApkQuestionRepoJpa apkQuestionRepoJpa) {
        super(genericCrudRepoJpa);
        this.apkQuestionRepoJpa = apkQuestionRepoJpa;
    }

    @Override
    public List<ApkQuestion> getApkQuestions() {
        return apkQuestionRepoJpa.getApkQuestions();
    }

    @Override
    public ApkQuestion findApkById(int idApk) {
        return apkQuestionRepoJpa.findApkById(idApk);
    }

    @Override
    public List<CalcGetApkDto> findApkQuestionsByCalcId(int calcId) {
        return apkQuestionRepoJpa.findApkQuestionsByCalcId(calcId);
    }
}
