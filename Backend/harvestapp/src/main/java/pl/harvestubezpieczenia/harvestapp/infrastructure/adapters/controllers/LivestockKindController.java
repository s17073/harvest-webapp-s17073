package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockKindDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.LivestockListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.LivestockKind;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;
import pl.harvestubezpieczenia.harvestapp.domain.services.LivestockKindService;

import java.util.List;

@RestController
@RequestMapping("livestockkind")
public class LivestockKindController {

    private final GenericService<LivestockKind, LivestockKindDto> genericService;
    private final LivestockKindService livestockKindService;

    @Autowired
    public LivestockKindController(GenericService<LivestockKind, LivestockKindDto> genericService, LivestockKindService livestockKindService) {
        this.genericService = genericService;
        this.livestockKindService = livestockKindService;
    }

    @GetMapping
    public ResponseEntity<List<LivestockKindDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivestockKindDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody LivestockKindDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody LivestockKindDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }

    @GetMapping("livestocklist")
    public ResponseEntity<List<LivestockListDto>> getLivestockList(){
        return livestockKindService.getLivestockList();
    }


}
