package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcGetPerson;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CalculationRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.CalculationRepoJpa;

import java.util.List;
import java.util.Map;

@Service
public class CalculationAdapterJpa implements CalculationRepo {

    private final CalculationRepoJpa calculationRepoJpa;

    public CalculationAdapterJpa(CalculationRepoJpa calculationRepoJpa) {
        this.calculationRepoJpa = calculationRepoJpa;
    }

    @Override
    public int startNewCalculation() {
        return calculationRepoJpa.startNewCalculation();
    }

    @Override
    public Calculation getCalculationById(int id) {
        return calculationRepoJpa.getCalculationById(id);
    }

    @Override
    public void saveCalculation(Calculation calculation) {
        calculationRepoJpa.save(calculation);
    }

    @Override
    public CalcGetPerson getPolicyHolder(int id) {
        return calculationRepoJpa.getPolicyHolder(id);
    }

    @Override
    public CalcGetPerson getPolicyInsured(int id) {
        return calculationRepoJpa.getPolicyInsured(id);
    }

    @Override
    public List<Map<String, Object>> getCrops(int calcId) {
        return calculationRepoJpa.getCrops(calcId);
    }

    @Override
    public List<Map<String, Object>> getCropCovers(int calcId, int cropId) {
        return calculationRepoJpa.getCropCovers(calcId, cropId);
    }

    @Override
    public List<Map<String, Object>> getCropLands(int calcId, int cropId) {
        return calculationRepoJpa.getCropLands(calcId, cropId);
    }

    @Override
    public Map<String, Object> getCrop(int calcId, int cropid) {
        return calculationRepoJpa.getCrop(calcId, cropid);
    }

}
