package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
