package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.model.Land;

@Repository
public interface LandRepoJpa extends JpaRepository<Land, Long> {


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM harvest.dzialka d where d.id_dzialka = :id", nativeQuery = true)
    void deleteLandById(@Param("id") int idDzialka);
}
