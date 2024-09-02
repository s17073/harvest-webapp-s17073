package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;

import java.util.List;

public interface CoverRepo extends GenericCrudRepo<Cover> {

    List<CoverListDto> getCoverListOfLivestock();

    List<CoverListDto> getCoverListOfCrops();

    Cover getCoverById(int id);
}
