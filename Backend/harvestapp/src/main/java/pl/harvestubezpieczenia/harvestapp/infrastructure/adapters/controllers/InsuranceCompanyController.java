package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.InsuranceCompanyDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("insurancecompany")
public class InsuranceCompanyController {

    private final GenericService<InsuranceCompany, InsuranceCompanyDto> genericService;

    public InsuranceCompanyController(GenericService<InsuranceCompany, InsuranceCompanyDto> genericService){
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<InsuranceCompanyDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceCompanyDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody InsuranceCompanyDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody InsuranceCompanyDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }

}
