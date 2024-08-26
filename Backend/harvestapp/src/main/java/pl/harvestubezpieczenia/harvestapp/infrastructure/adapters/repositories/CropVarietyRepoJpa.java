package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;

import java.util.List;

@Repository
public interface CropVarietyRepoJpa extends GenericCrudRepoJpa<CropVariety> {

    @Query(value = "select g.id_gatunek, g.nazwa_gatunku from harvest.gatunek g inner join harvest.uprawa_gatunek ug on ug.id_gatunek = g.id_gatunek where data_usuniecia is null and czy_aktywna = true and ug.id_rodzaj_uprawy = :cropid",nativeQuery = true)
    List<CropVarietyListDto> getCropVarietyNames(@Param("cropid") int cropId);
}
