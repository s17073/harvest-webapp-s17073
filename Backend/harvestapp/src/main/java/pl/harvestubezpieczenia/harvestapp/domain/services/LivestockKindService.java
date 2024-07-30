package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

@Service
public class LivestockKindService extends GenericService<LivestockKind, LivestockKindDto> {

    @Autowired
    public LivestockKindService(GenericCrudRepo<LivestockKind> repo, GenericMapper<LivestockKind, LivestockKindDto> map) {
        super(repo, map);
    }
}
