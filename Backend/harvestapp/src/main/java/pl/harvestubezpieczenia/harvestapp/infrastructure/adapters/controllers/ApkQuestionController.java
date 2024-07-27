package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("apk")
public class ApkQuestionController {

    private final String mapper = "apkQuestionMapper";
    private final String repository = "apkQuestionRepoJpa";

    private final GenericService<ApkQuestion, ApkQuestionDto> genericService;

    @Autowired
    public ApkQuestionController(GenericService<ApkQuestion, ApkQuestionDto> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<ApkQuestionDto>> getAllItems() {
        return genericService.getAllItems(mapper, repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApkQuestionDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(mapper, repository, id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody ApkQuestionDto dto) {
        return genericService.addItem(mapper, repository, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(repository, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody ApkQuestionDto dto, @PathVariable int id){
        return genericService.updateItem(mapper, repository, dto, id);
    }


}
