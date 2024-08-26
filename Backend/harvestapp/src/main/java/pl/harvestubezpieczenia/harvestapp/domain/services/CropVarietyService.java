package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyListDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropVarietyRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.List;

@Service
public class CropVarietyService extends GenericService<CropVariety, CropVarietyDto> {

    private final CropVarietyRepo cropVarietyRepo;

    @Autowired
    public CropVarietyService(GenericCrudRepo<CropVariety> repo, GenericMapper<CropVariety, CropVarietyDto> map, CropVarietyRepo cropVarietyRepo) {
        super(repo, map);
        this.cropVarietyRepo = cropVarietyRepo;
    }

    public ResponseEntity<List<CropVarietyListDto>> getCropVarietyNames(int cropId) {
        List<CropVarietyListDto> cropVarietyListDto = cropVarietyRepo.getCropVarietyNames(cropId);

        if(cropVarietyListDto.isEmpty()){
            return new ResponseEntity<>(cropVarietyListDto, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cropVarietyListDto, HttpStatus.OK);
    }
}
