package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.Map;

@Service
public class LivestockKindService extends GenericService<LivestockKind, LivestockKindDTO> {

    public LivestockKindService(Map<String, GenericCrudRepo<LivestockKind>> genericCrudRepos, Map<String,GenericMapper<LivestockKind, LivestockKindDTO>> genericMappers){
        super(genericCrudRepos, genericMappers);
    }

}
