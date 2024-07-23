package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.CropKindService;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("cropkind")
public class CropKindController {


    private final GenericService<CropKind, CropKindDTO> genericService;
    private final CropKindService cropKindService;


    @Autowired
    public CropKindController(GenericService<CropKind, CropKindDTO> genericService, CropKindService cropKindService) {
        this.genericService = genericService;
        this.cropKindService = cropKindService;
    }


    @GetMapping
    public ResponseEntity<List<CropKindDTO>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropKindDTO> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropKindDTO cropKindDTO) {
        return cropKindService.addItem(cropKindDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropKindDTO cropKindDTO, @PathVariable int id){
        return genericService.updateItem(cropKindDTO, id);
    }

}
