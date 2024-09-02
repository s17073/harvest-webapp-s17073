package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.model.Livestock;

@Repository
public interface LivestockRepoJpa extends JpaRepository<Livestock, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from harvest.ochrona_zwierze where id_zwierze = :id", nativeQuery = true)
    void removeCoversByLivestockId(@Param("id") int livestockId);

    @Modifying
    @Transactional
    @Query(value = "delete from harvest.zwierze z where id_zwierze = :id", nativeQuery = true)
    void removeLivestockById(@Param("id") int livestockId);

    @Query(value = "SELECT * FROM harvest.zwierze z where z.id_zwierze = :id ", nativeQuery = true)
    Livestock findLivestockById(@Param("id") int livestockId);
}
