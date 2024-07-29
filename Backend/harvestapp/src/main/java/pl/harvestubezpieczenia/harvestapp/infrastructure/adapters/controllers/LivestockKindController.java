package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("livestockkind")
public class LivestockKindController {

    private final String mapper = "livestockKindMapper";
    private final String repository = "livestockKindRepoJpa";

    private final GenericService<LivestockKind, LivestockKindDTO> genericService;

    @Autowired
    public LivestockKindController(GenericService<LivestockKind, LivestockKindDTO> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<LivestockKindDTO>> getAllItems() {
        return genericService.getAllItems(mapper, repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivestockKindDTO> getItemByID(@PathVariable int id){
        return genericService.getItemByID(mapper, repository, id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody LivestockKindDTO dto) {
        return genericService.addItem(mapper, repository, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(repository, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody LivestockKindDTO dto, @PathVariable int id){
        return genericService.updateItem(mapper, repository, dto, id);
    }


}
