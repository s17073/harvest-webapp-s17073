package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.CropKindService;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("cropkind")
public class CropKindController {

    private final GenericService<CropKind, CropKindDto> genericService;
    private final CropKindService cropKindService;

    @Autowired
    public CropKindController(GenericService<CropKind, CropKindDto> genericService, CropKindService cropKindService) {
        this.genericService = genericService;
        this.cropKindService = cropKindService;
    }

    @GetMapping
    public ResponseEntity<List<CropKindDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropKindDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropKindDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropKindDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }

    @GetMapping("/croplist")
    public ResponseEntity<List<CropKindListDto>> getListOfCropKindNames() {
        return cropKindService.getListOfCropKindNames();
    }

}
