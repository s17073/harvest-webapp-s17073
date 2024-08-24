package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.ApkQuestionDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.ApkQuestion;
import pl.harvestubezpieczenia.harvestapp.domain.services.ApkQuestionService;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("apk")
public class ApkQuestionController {

    private final GenericService<ApkQuestion, ApkQuestionDto> genericService;
    private final ApkQuestionService apkQuestionService;

    public ApkQuestionController(GenericService<ApkQuestion, ApkQuestionDto> genericService, ApkQuestionService apkQuestionService) {
        this.genericService = genericService;
        this.apkQuestionService = apkQuestionService;
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

    @GetMapping("/questions")
    public ResponseEntity<List<ApkQuestionDto>> getApkQuestions() {
        return apkQuestionService.getApkQuestions();
    }


}
