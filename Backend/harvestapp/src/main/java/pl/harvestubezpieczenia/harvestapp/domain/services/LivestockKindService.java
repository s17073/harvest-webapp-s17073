package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockListDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.LivestockKindRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivestockKindService extends GenericService<LivestockKind, LivestockKindDto> {

    private final LivestockKindRepo livestockKindRepo;

    @Autowired
    public LivestockKindService(GenericCrudRepo<LivestockKind> repo, GenericMapper<LivestockKind, LivestockKindDto> map, LivestockKindRepo livestockKindRepo) {
        super(repo, map);
        this.livestockKindRepo = livestockKindRepo;
    }

    public ResponseEntity<List<LivestockListDto>> getLivestockList() {
        List<LivestockListDto> livestockListDtos = new ArrayList<>();

        for(LivestockKind l: livestockKindRepo.getLivestockList()){
            LivestockListDto livestockListDto = new LivestockListDto();

            livestockListDto.setId(l.getIdRodzajZwierzecia());
            livestockListDto.setNazwa(l.getNazwa().nazwa());

            livestockListDtos.add(livestockListDto);

        }

        return new ResponseEntity<>(livestockListDtos, HttpStatus.OK);

    }
}
