package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;

import java.util.List;

@Repository
public interface CoverRepoJpa extends GenericCrudRepoJpa<Cover> {

    @Query(value = "select * from harvest.ochrona o where czy_zwierze = true and data_usuniecia is null and czy_aktywna = true", nativeQuery = true)
    List<CoverListDto> getCoverListOfLivestock();

    @Query(value = "select * from harvest.ochrona o where czy_uprawa = true and data_usuniecia is null and czy_aktywna = true", nativeQuery = true)
    List<CoverListDto> getCoverListOfCrops();

    @Query(value = "select * from harvest.ochrona o where id_ochrona = :idcover", nativeQuery = true)
    Cover getCoverById(@Param("idcover") int id);
}
