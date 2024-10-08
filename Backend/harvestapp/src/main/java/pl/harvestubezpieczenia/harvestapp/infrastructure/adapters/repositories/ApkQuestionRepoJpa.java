package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcGetApkDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;

import java.util.List;

@Repository
public interface ApkQuestionRepoJpa extends GenericCrudRepoJpa<ApkQuestion> {

    @Query(value = "select * from harvest.apk where czy_aktywna = true and data_usuniecia is null", nativeQuery = true)
    List<ApkQuestion> getApkQuestions();

    @Query(value = "SELECT * FROM harvest.apk where czy_aktywna = true and data_usuniecia is null and id_apk = :id", nativeQuery = true)
    ApkQuestion findApkById(@Param("id") int idApk);

    @Query(value = "select ak.id_apk as id, ak.odpowiedz from harvest.apk a inner join harvest.apk_kalkulacja ak on a.id_apk = ak.id_apk inner join harvest.kalkulacja k ON ak.id_kalkulacja = k.id_kalkulacja where k.id_kalkulacja = :calcid", nativeQuery = true)
    List<CalcGetApkDto> findApkQuestionsByCalcId(@Param("calcid") int calcId);
}
