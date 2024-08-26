package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverListDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CoverRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.List;

@Service
public class CoverService extends GenericService<Cover, CoverDto> {

    private final CoverRepo coverRepo;

    @Autowired
    public CoverService(GenericCrudRepo<Cover> repo, GenericMapper<Cover, CoverDto> map, CoverRepo coverRepo) {
        super(repo, map);
        this.coverRepo = coverRepo;
    }

    public ResponseEntity<List<CoverListDto>> getCoverList(String coveredType) {
        List<CoverListDto> coverListDtos;

        if(coveredType.equals("zwierzeta"))
            coverListDtos = coverRepo.getCoverListOfLivestock();
        else if(coveredType.equals("uprawy"))
            coverListDtos = coverRepo.getCoverListOfCrops();
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(coverListDtos.isEmpty()) return new ResponseEntity<>(coverListDtos, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(coverListDtos, HttpStatus.OK);
    }
}
