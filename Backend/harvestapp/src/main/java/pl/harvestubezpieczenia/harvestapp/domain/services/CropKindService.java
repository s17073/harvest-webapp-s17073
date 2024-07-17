package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropKindRepo;

import java.util.List;

@Service
public class CropKindService {


    private final CropKindRepo cropKindRepo;

    @Autowired
    public CropKindService(CropKindRepo cropKindRepo) {
        this.cropKindRepo = cropKindRepo;
    }


    public ResponseEntity<List<CropKind>> getAllCropKinds() {
        return new ResponseEntity<>(cropKindRepo.getAllCropKinds(), HttpStatus.OK);
    }

    public ResponseEntity<String> addCropKind(CropKind cropKind) {
        cropKindRepo.addCropKind(cropKind);
        return new ResponseEntity<>(cropKind.getNazwa_uprawy() + " successfully added to the database.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeCropKindById(int id) {
        boolean deleted = false;
        String deletedCropKind = "";
        for(CropKind ck: cropKindRepo.getAllCropKinds()){
            if(ck.getId_rodzaj_uprawy() == id) {
                    deletedCropKind = ck.getNazwa_uprawy();
                    cropKindRepo.removeCropKind(ck);
                    deleted = true;
            }
        }
        if(deleted){
            return new ResponseEntity<>(deletedCropKind + " successfully deleted from the database", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("crop kind id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
