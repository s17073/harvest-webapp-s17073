package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.ApkCalculation;

public interface ApkCalculationRepo {
    void saveApkCalculation(ApkCalculation apkCalculation);

    ApkCalculation getApkCalculationByIds(int idKalkulacja, int idApk);
}
