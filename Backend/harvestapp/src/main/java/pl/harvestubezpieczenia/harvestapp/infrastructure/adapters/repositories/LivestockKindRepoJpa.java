package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;

import java.util.List;

@Repository
public interface LivestockKindRepoJpa extends GenericCrudRepoJpa<LivestockKind> {

    @Query(value = "SELECT * FROM harvest.rodzaj_zwierzecia",nativeQuery = true)
    List<LivestockKind> getLivestockList();

    @Query(value = "SELECT * FROM harvest.rodzaj_zwierzecia where id_rodzaj_zwierzecia = :id", nativeQuery = true)
    LivestockKind getLivestockById(@Param("id") int idRodzajZwierzecia);
}
