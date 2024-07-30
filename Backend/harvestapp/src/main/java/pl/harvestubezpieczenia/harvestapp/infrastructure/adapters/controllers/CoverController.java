package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CoverDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Cover;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@Controller
@RequestMapping("cover")
public class CoverController {

    private final GenericService<Cover, CoverDto> genericService;

    @Autowired
    public CoverController(GenericService<Cover, CoverDto> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<CoverDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody CoverDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody CoverDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }

}
