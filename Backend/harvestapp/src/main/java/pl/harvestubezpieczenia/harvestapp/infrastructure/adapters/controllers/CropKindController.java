package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.CropKindService;

import java.util.List;

@RestController
@RequestMapping("cropkind")
public class CropKindController {

    private final CropKindService cropKindService;

    @Autowired
    public CropKindController(CropKindService cropKindService) {
        this.cropKindService = cropKindService;
    }

    @GetMapping
    public ResponseEntity<List<CropKind>> getAllCropKinds() {
        return cropKindService.getAllCropKinds();
    }

    @PostMapping
    public ResponseEntity<String> addCropKind(@RequestBody CropKind cropKind){
        return cropKindService.addCropKind(cropKind);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCropKindById(@PathVariable int id){
        return cropKindService.removeCropKindById(id);
    }

}
