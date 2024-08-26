package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.services.CropVarietyService;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("cropvariety")
public class CropVarietyController {

    private final GenericService<CropVariety, CropVarietyDto> genericService;
    private final CropVarietyService cropVarietyService;

    public CropVarietyController(GenericService<CropVariety, CropVarietyDto> genericService, CropVarietyService cropVarietyService) {
        this.genericService = genericService;
        this.cropVarietyService = cropVarietyService;
    }

    @GetMapping
    public ResponseEntity<List<CropVarietyDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropVarietyDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropVarietyDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropVarietyDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }

    @GetMapping("/cropvarietylist")
    public ResponseEntity<List<CropVarietyListDto>> getCropVarietyNames(@RequestParam(value = "cropid", required = true) int cropId) {
        return cropVarietyService.getCropVarietyNames(cropId);
    }
}
