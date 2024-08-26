package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;

import java.util.List;

public interface SoilClassRepo extends GenericCrudRepo<SoilClass>{
    List<SoilClassListDto> getSoilClassNames();
}
