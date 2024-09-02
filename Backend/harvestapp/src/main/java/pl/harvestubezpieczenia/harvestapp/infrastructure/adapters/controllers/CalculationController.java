package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.*;
import pl.harvestubezpieczenia.harvestapp.domain.services.CalculationService;

import java.util.List;

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

    @PutMapping("{id}/insuranceperiod")
    public ResponseEntity<String> addInsurancePeriod(@RequestBody CalcInsurancePeriodDto dto, @PathVariable("id") Integer id){
        return calculationService.addInsurancePeriod(dto, id);
    }

    @GetMapping("{id}/insuranceperiod")
    public ResponseEntity<CalcGetInsurancePeriodDto> getInsurancePeriod(@PathVariable("id") int id){
        return calculationService.findApkQuestionsByCalcId(id);
    }

    @PutMapping("{id}/personaldata")
    public ResponseEntity<String> addPersonalData(@RequestBody CalcPersonalDataDto dto, @PathVariable("id") Integer id){
        return calculationService.addPersonalData(dto, id);
    }

    @GetMapping("{id}/personaldata")
    public ResponseEntity<CalcGetPersonalDataDto> getPersonalData(@PathVariable("id") int id){
        return calculationService.getPersonalData(id);
    }

    @PutMapping("{id}/crop")
    public ResponseEntity<String> addCrop(@RequestBody CalcCropDto dto, @PathVariable("id") Integer id){
        return calculationService.addCrop(dto, id);
    }

    @PutMapping("{calcid}/crop/{cropid}")
    public ResponseEntity<String> modifyCrop(@RequestBody CalcCropDto dto, @PathVariable("calcid") Integer idCalc, @PathVariable("cropid") Integer idCrop){
        return calculationService.modifyCrop(dto, idCalc, idCrop);
    }

    @GetMapping("{id}/crops")
    public ResponseEntity<List<CalcGetCropDto>> getCrops(@PathVariable("id") Integer id){
        return calculationService.getCrops(id);
    }

    @GetMapping("{calcid}/crop/{cropid}")
    public ResponseEntity<CalcGetCropDto> getCrop(@PathVariable("calcid") int calcId, @PathVariable("cropid") int cropid){
        return calculationService.getCrop(calcId, cropid);
    }

    @DeleteMapping("{calcid}/crop/{cropid}")
    public ResponseEntity<String> deleteCrop(@PathVariable("calcid") int calcId, @PathVariable("cropid") int cropid){
        return calculationService.deleteCrop(calcId, cropid);
    }

    @PutMapping("{calcid}/livestock")
    public ResponseEntity<String> addLivestock(@RequestBody CalcLivestockDto livestockDto ,@PathVariable("calcid") int calcId){
        return calculationService.addLivestock(livestockDto, calcId);
    }

    @GetMapping("{calcid}/livestock")
    public ResponseEntity<List<CalcLivestockDto>> getLivestock(@PathVariable("calcid") int calcId){
        return calculationService.getLivestock(calcId);
    }

    @GetMapping("{calcid}/livestock/{livestockid}")
    public ResponseEntity<CalcLivestockDto> getLivestockById(@PathVariable("calcid") int calcId, @PathVariable("livestockid") int livestockId){
        return calculationService.getLivestockById(livestockId);
    }

    @DeleteMapping("{calcid}/livestock/{livestockid}")
    public ResponseEntity<String> deleteLivestock(@PathVariable("calcid") int calcId, @PathVariable("livestockid") int livestockId){
        return calculationService.deleteLivestock(livestockId);
    }

    @PutMapping("{calcid}/livestock/{livestockid}")
    public ResponseEntity<String> modifyLivestock(@RequestBody CalcLivestockDto livestockDto, @PathVariable("livestockid") int livestockId){
        return calculationService.modifyLivestock(livestockDto, livestockId);
    }

}
