package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;

@Repository
public interface CalculationRepoJpa extends JpaRepository<Calculation, Long> {

    default int startNewCalculation(){
        Calculation calculation = new Calculation();
        save(calculation);
        return calculation.getIdKalkulacja();
    }

    @Query(value = "SELECT * FROM harvest.kalkulacja where id_kalkulacja = :id", nativeQuery = true)
    Calculation getCalculationById(@Param("id") int id);
}
