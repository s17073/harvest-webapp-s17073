package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.PartOfTerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.TerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.services.TerytService;

import java.util.List;

@RestController
@RequestMapping("teryt")
public class TerytController {

    private final TerytService service;

    @Autowired
    public TerytController(TerytService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TerytDto>> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerytDto> getItemById(@PathVariable int id){
        return service.getItemById(id);
    }

    @GetMapping("/wojewodztwa")
    public ResponseEntity<List<PartOfTerytDto>> getWojewodztwa(){
        return service.getWojewodztwa();
    }

    @GetMapping("/powiaty")
    public ResponseEntity<List<PartOfTerytDto>> getPowiaty(@RequestParam(value = "teryt") String teryt){
        return service.getPowiaty(teryt);
    }
    @GetMapping("/gminy")
    public ResponseEntity<List<PartOfTerytDto>> getGminy(@RequestParam(value = "teryt") String teryt){
        return service.getGminy(teryt);
    }

}
