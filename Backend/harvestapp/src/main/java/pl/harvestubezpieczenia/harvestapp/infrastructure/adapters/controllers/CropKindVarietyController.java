package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("ckv")
public class CropKindVarietyController {

    private final GenericService<CropKindVariety, CropKindVarietyDto> genericService;

    @Autowired
    public CropKindVarietyController(GenericService<CropKindVariety, CropKindVarietyDto> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<CropKindVarietyDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropKindVarietyDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropKindVarietyDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropKindVarietyDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }
}
