package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.model.Crop;

@Repository
public interface CropRepoJpa extends JpaRepository<Crop, Long> {

    @Query(value = "SELECT * FROM harvest.uprawa where id_uprawa = :id", nativeQuery = true)
    Crop getCropById(@Param("id") int idCrop);

    @Modifying
    @Transactional
    @Query("DELETE FROM Crop where idUprawa = :id")
    void removeCropById(@Param("id") int idUprawa);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM harvest.ochrona_uprawa ou where ou.id_uprawa = :id", nativeQuery = true)
    void removeCoverByCropId(@Param("id") int idUprawa);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM harvest.dzialka d where d.id_uprawa = :id", nativeQuery = true)
    void removeLandByCropId(@Param("id") int idUprawa);
}