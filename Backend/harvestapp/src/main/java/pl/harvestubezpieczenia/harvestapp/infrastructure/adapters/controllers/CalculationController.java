package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcInsurancePeriodDto;
import pl.harvestubezpieczenia.harvestapp.domain.services.CalculationService;

@Controller
@RequestMapping("calculation")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/new")
    public ResponseEntity<Integer> startNewCalculation(){
        return calculationService.startNewCalculation();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addInsurancePeriod(@RequestBody CalcInsurancePeriodDto dto, @PathVariable("id") Integer id){
        return calculationService.addInsurancePeriod(dto, id);
    }
}
