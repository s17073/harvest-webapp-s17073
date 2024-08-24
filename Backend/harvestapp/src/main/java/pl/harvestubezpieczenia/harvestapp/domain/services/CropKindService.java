package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindListDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.CropKindListMapper;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropKindService extends GenericService<CropKind, CropKindDto>{

    private final CropKindListMapper cropKindListMapper;

    @Autowired
    public CropKindService(GenericCrudRepo<CropKind> repo, GenericMapper<CropKind, CropKindDto> map, CropKindListMapper cropKindListMapper) {
        super(repo, map);
        this.cropKindListMapper = cropKindListMapper;
    }

    public ResponseEntity<List<CropKindListDto>> getListOfCropKindNames() {
        List<CropKindListDto> cropKindListDtos = new ArrayList<>();

        for(CropKind ck: repo.getAllItems()){
            if(ck.getDataModyfikacji().dataUsuniecia() == null) cropKindListDtos.add(cropKindListMapper.mapToDto(ck));
        }
        if(cropKindListDtos.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cropKindListDtos, HttpStatus.OK);
    }
}
