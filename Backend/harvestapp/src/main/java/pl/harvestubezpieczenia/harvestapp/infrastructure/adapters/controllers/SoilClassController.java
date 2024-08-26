package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.SoilClassListDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.SoilClass;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;
import pl.harvestubezpieczenia.harvestapp.domain.services.SoilClassService;

import java.util.List;

@RestController
@RequestMapping("soilclass")
public class SoilClassController {

    private final GenericService<SoilClass, SoilClassDto> genericService;
    private final SoilClassService soilClassService;

    @Autowired
    public SoilClassController(GenericService<SoilClass, SoilClassDto> genericService, SoilClassService soilClassService) {
        this.genericService = genericService;
        this.soilClassService = soilClassService;
    }


    @GetMapping
    public ResponseEntity<List<SoilClassDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SoilClassDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody SoilClassDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody SoilClassDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }

    @GetMapping("/soilclasslist")
    public ResponseEntity<List<SoilClassListDto>> getSoilClassNames(){
        return soilClassService.getSoilClassNames();
    }
}
