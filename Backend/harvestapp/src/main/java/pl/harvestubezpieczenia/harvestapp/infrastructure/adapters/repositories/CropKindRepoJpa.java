package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;

@Repository
public interface CropKindRepoJpa extends GenericCrudRepoJpa<CropKind> {

    @Query(value = "select * from harvest.rodzaj_uprawy ru where id_rodzaj_uprawy = :idcrop", nativeQuery = true)
    CropKind getCropKindById(@Param("idcrop") int id);
}
