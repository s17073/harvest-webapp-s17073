package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropVarietyDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("cropvariety")
public class CropVarietyController {

    private final String mapper = "cropVarietyMapper";
    private final String repository = "cropVarietyRepoJpa";

    private final GenericService<CropVariety, CropVarietyDto> genericService;

    @Autowired
    public CropVarietyController(GenericService<CropVariety, CropVarietyDto> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<CropVarietyDto>> getAllItems() {
        return genericService.getAllItems(mapper, repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropVarietyDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(mapper, repository, id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropVarietyDto dto) {
        return genericService.addItem(mapper, repository, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(repository, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropVarietyDto dto, @PathVariable int id){
        return genericService.updateItem(mapper, repository, dto, id);
    }
}
