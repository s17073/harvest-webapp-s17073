package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcGetPerson;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;

import java.util.List;
import java.util.Map;

public interface CalculationRepo {
    int startNewCalculation();

    Calculation getCalculationById(int id);

    void saveCalculation(Calculation calculation);

    CalcGetPerson getPolicyHolder(int id);

    CalcGetPerson getPolicyInsured(int id);

    List<Map<String, Object>> getCrops(int calcId);

    List<Map<String, Object>> getCropCovers(int calcId, int cropId);

    List<Map<String, Object>> getCropLands(int calcId, int cropId);

    Map<String, Object> getCrop(int calcId, int cropid);
}
