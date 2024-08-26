package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkCalculation;
import pl.harvestubezpieczenia.harvestapp.domain.ports.ApkCalculationRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.ApkCalculationRepoJpa;

@Service
public class ApkCalculationAdapterJpa implements ApkCalculationRepo {

    private final ApkCalculationRepoJpa apkCalculationRepoJpa;

    public ApkCalculationAdapterJpa(ApkCalculationRepoJpa apkCalculationRepoJpa) {
        this.apkCalculationRepoJpa = apkCalculationRepoJpa;
    }


    public void saveApkCalculation(ApkCalculation apkCalculation) {
        apkCalculationRepoJpa.save(apkCalculation);
    }

    @Override
    public ApkCalculation getApkCalculationByIds(int idKalkulacja, int idApk) {
        return apkCalculationRepoJpa.getApkCalculationByIds(idKalkulacja, idApk);
    }
}
