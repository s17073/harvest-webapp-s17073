package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropVarietyName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropKindVarietyService extends  GenericService<CropKindVariety, CropKindVarietyDto> {

    private final GenericCrudRepo<CropKind> cropKindRepo;
    private final GenericCrudRepo<CropVariety> cropVarietyRepo;

    public CropKindVarietyService(GenericCrudRepo<CropKindVariety> repo,
                                  GenericMapper<CropKindVariety, CropKindVarietyDto> map,
                                  GenericCrudRepo<CropKind> cropKindRepo, GenericCrudRepo<CropVariety> cropVarietyRepo) {
        super(repo, map);
        this.cropKindRepo = cropKindRepo;
        this.cropVarietyRepo = cropVarietyRepo;
    }

    @Override
    public ResponseEntity<List<CropKindVarietyDto>> getAllItems() {
        List<CropKindVarietyDto> activeItemsDto = new ArrayList<>();

        for(CropKindVariety ckv: repo.getAllItems()){
            if (ckv.getCropVariety().getDataModyfikacji().dataUsuniecia() == null
            && ckv.getCropKind().getDataModyfikacji().dataUsuniecia() == null) activeItemsDto.add(map.mapToDto(ckv));
        }
        if(activeItemsDto.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(activeItemsDto, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addItem(CropKindVarietyDto dto) {
        for(CropKind ck: cropKindRepo.getAllItems()){
            if(ck.getId() == dto.getIdUprawa()){
                dto.setUprawa(ck);
                break;
            }
        }
        CropVariety cropVariety = new CropVariety();
        cropVariety.setNazwaGatunku(new CropVarietyName(dto.getNazwaGatunku()));
        cropVariety.setCzyAktywna(dto.isCzyAktywna());
        cropVariety.setDataModyfikacji(new ModificationDate());

        dto.setGatunek(cropVariety);

        repo.addItem(map.mapToEntity(dto));

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<String> updateItem(CropKindVarietyDto dto, int id) {
        String nameOfRemovedItem;
        CropVariety itemToUpdate = findCropVarietyForModify(id);

        if(itemToUpdate == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToUpdate.getDataModyfikacji().dataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }
        nameOfRemovedItem = itemToUpdate.getName();

        itemToUpdate.setDataModyfikacji(new ModificationDate(itemToUpdate.getDataModyfikacji().dataDodania()));

        addItem(dto);
        cropVarietyRepo.addItem(itemToUpdate);

        return new ResponseEntity<>(nameOfRemovedItem + " successfully updated.", HttpStatus.OK);
    }

    public CropVariety findCropVarietyForModify(int id) {
        for(CropVariety cv: cropVarietyRepo.getAllItems()){
            if(cv.getId() == id){
                return cv;
            }
        }
        return null;
    }
}
