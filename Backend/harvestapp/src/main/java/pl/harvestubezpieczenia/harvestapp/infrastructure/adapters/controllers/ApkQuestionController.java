package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("apk")
public class ApkQuestionController {

    private final GenericService<ApkQuestion, ApkQuestionDto> genericService;

    public ApkQuestionController(GenericService<ApkQuestion, ApkQuestionDto> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<ApkQuestionDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApkQuestionDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody ApkQuestionDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody ApkQuestionDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }


}
