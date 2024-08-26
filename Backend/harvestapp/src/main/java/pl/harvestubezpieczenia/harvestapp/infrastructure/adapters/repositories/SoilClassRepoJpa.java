package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;

import java.util.List;

@Repository
public interface SoilClassRepoJpa extends GenericCrudRepoJpa<SoilClass> {

    @Query(value = "select id_klasa_gleby, klasa_gleby, opis from harvest.klasa_gleby where data_usuniecia is null and czy_aktywna = true order by klasa_gleby ", nativeQuery = true)
    List<SoilClassListDto> getSoilClassNames();
}
