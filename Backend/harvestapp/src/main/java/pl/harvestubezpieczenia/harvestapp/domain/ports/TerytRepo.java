package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.PartOfTerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;

import java.util.List;

public interface TerytRepo {

    List<Teryt> getAllItems();

    List<PartOfTerytDto> getWojewodztwa();

    List<PartOfTerytDto> getPowiaty(String teryt);

    List<PartOfTerytDto> getGminy(String teryt);
}
