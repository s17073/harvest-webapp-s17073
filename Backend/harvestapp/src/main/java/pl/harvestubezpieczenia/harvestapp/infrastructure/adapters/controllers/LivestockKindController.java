package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("livestockkind")
public class LivestockKindController {

    private final GenericService<LivestockKind, LivestockKindDTO> genericService;

    public LivestockKindController(GenericService<LivestockKind, LivestockKindDTO> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<LivestockKindDTO>> getAllItems() {
        return genericService.getAllItems("livestockKindMapper", "livestockKindRepoJpa");
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivestockKindDTO> getItemByID(@PathVariable int id){
        return genericService.getItemByID("livestockKindMapper", "livestockKindRepoJpa", id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody LivestockKindDTO livestockKindDTO) {
        return genericService.addItem("livestockKindMapper", "livestockKindRepoJpa", livestockKindDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById("livestockKindRepoJpa", id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody LivestockKindDTO livestockKindDTO, @PathVariable int id){
        return genericService.updateItem("livestockKindMapper", "livestockKindRepoJpa", livestockKindDTO, id);
    }


}
