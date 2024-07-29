package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.InsuranceCompanyDto;

import java.util.List;

@RestController
@RequestMapping("insurancecompany")
public class InsuranceCompanyController {

    private final String mapper = "insuranceCompanyMapper";
    private final String repository = "insuranceCompanyRepoJpa";

    private final GenericService<InsuranceCompany, InsuranceCompanyDto> genericService;

    public InsuranceCompanyController(GenericService<InsuranceCompany, InsuranceCompanyDto> genericService){
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<InsuranceCompanyDto>> getAllItems() {
        return genericService.getAllItems(mapper, repository);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceCompanyDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(mapper, repository, id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody InsuranceCompanyDto dto) {
        return genericService.addItem(mapper, repository, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(repository, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody InsuranceCompanyDto dto, @PathVariable int id){
        return genericService.updateItem(mapper, repository, dto, id);
    }

}
