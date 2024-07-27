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


    private final GenericService<CropKind, CropKindDTO> genericService;

    @Autowired
    public CropKindController(GenericService<CropKind, CropKindDTO> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<CropKindDTO>> getAllItems() {
        return genericService.getAllItems("cropKindMapper", "cropKindRepoJpa");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropKindDTO> getItemByID(@PathVariable int id){
        return genericService.getItemByID("cropKindMapper", "cropKindRepoJpa", id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CropKindDTO cropKindDTO) {
        return genericService.addItem("cropKindMapper", "cropKindRepoJpa", cropKindDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById("cropKindRepoJpa", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CropKindDTO cropKindDTO, @PathVariable int id){
        return genericService.updateItem("cropKindMapper", "cropKindRepoJpa", cropKindDTO, id);
    }

}
