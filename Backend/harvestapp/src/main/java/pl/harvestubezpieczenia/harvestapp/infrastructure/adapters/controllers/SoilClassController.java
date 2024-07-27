package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("soilclass")
public class SoilClassController {

    private final String mapper = "soilClassMapper";
    private final String repository = "soilClassRepoJpa";

    private final GenericService <SoilClass, SoilClassDTO> genericService;

    @Autowired
    public SoilClassController(GenericService<SoilClass, SoilClassDTO> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<SoilClassDTO>> getAllItems() {
        return genericService.getAllItems(mapper, repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoilClassDTO> getItemByID(@PathVariable int id){
        return genericService.getItemByID(mapper, repository, id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody SoilClassDTO dto) {
        return genericService.addItem(mapper, repository, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(repository, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody SoilClassDTO dto, @PathVariable int id){
        return genericService.updateItem(mapper, repository, dto, id);
    }
}
