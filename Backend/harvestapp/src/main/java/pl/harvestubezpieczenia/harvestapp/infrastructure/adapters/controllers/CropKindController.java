package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("cropkind")
public class CropKindController {

    private final String mapper = "CropKindMapper";
    private final String repository = "CropKindRepoJpa";

    private final GenericService<CropKind, CropKindDTO> genericService;

    @Autowired
    public CropKindController(GenericService<CropKind, CropKindDTO> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<CropKindDTO>> getAllItems() {
        return genericService.getAllItems(mapper, repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropKindDTO> getItemByID(@PathVariable int id){
        return genericService.getItemByID(mapper, repository, id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropKindDTO dto) {
        return genericService.addItem(mapper, repository, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(repository, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropKindDTO dto, @PathVariable int id){
        return genericService.updateItem(mapper, repository, dto, id);
    }

}
