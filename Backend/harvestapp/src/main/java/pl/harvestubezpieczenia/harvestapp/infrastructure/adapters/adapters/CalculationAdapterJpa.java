package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CalculationRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.CalculationRepoJpa;

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
}
