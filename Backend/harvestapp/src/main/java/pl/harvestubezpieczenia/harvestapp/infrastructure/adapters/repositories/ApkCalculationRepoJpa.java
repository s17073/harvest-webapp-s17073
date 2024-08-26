package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkCalculation;

public interface ApkCalculationRepoJpa extends JpaRepository<ApkCalculation, Long> {

    @Query(value = "select * from harvest.apk_kalkulacja where id_apk = :idapk and id_kalkulacja = :idkalkulacja", nativeQuery = true)
    ApkCalculation getApkCalculationByIds(@Param("idkalkulacja") int idKalkulacja, @Param("idapk") int idApk);
}
