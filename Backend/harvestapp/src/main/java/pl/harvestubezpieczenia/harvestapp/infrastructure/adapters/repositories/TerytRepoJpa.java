package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;

import java.util.List;

@Repository
public interface TerytRepoJpa extends JpaRepository<Teryt, Long> {

    default List<Teryt> getAllItems(){
        return findAll();
    }

}
