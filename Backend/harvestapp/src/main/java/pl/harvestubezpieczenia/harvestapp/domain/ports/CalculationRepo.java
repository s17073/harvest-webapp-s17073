package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;

public interface CalculationRepo {
    int startNewCalculation();

    Calculation getCalculationById(int id);

    void saveCalculation(Calculation calculation);
}
