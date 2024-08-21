package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.PartOfTerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;

import java.util.List;

@Repository
public interface TerytRepoJpa extends JpaRepository<Teryt, Long> {

    default List<Teryt> getAllItems(){
        return findAll();
    }

    @Query(value = "select kod_teryt , nazwa from harvest.teryt where typ = 'województwo'", nativeQuery = true)
    List<PartOfTerytDto> getWojewodztwa(
    );

    @Query(value = "select kod_teryt, nazwa from harvest.teryt t where typ in('powiat','miasto na prawach powiatu','miasto stołeczne, na prawach powiatu') and wojewodztwo = :teryt", nativeQuery = true)
    List<PartOfTerytDto> getPowiaty(@Param("teryt")String teryt);

    @Query(value = "select kod_teryt , nazwa||' - '||typ as nazwa from harvest.teryt where gmina is not null and left(kod_teryt,4) =:teryt", nativeQuery = true)
    List<PartOfTerytDto> getGminy(@Param("teryt") String teryt);
}
