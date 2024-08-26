package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassListDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.SoilClassRepo;

import java.util.List;

@Service
public class SoilClassService extends GenericService<SoilClass, SoilClassDto> {
    private final SoilClassRepo soilClassRepo;

    @Autowired
    public SoilClassService(GenericCrudRepo<SoilClass> repo, GenericMapper<SoilClass, SoilClassDto> map, SoilClassRepo soilClassRepo) {
        super(repo, map);
        this.soilClassRepo = soilClassRepo;
    }

    public ResponseEntity<List<SoilClassListDto>> getSoilClassNames() {
        List<SoilClassListDto> soilClassListDto = soilClassRepo.getSoilClassNames();

        if (soilClassListDto.isEmpty()) {
            return new ResponseEntity<>(soilClassListDto, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(soilClassListDto, HttpStatus.OK);

    }
}
