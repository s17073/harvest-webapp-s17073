package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;
import pl.harvestubezpieczenia.harvestapp.domain.ports.TerytRepo;

import java.util.List;

@Repository
public interface TerytRepoJpa extends JpaRepository<Teryt, Long>, TerytRepo {

    default List<Teryt> getAllItems(){
        return findAll();
    }

}
