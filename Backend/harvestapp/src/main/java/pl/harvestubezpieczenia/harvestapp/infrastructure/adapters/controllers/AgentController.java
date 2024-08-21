package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AgentDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;
import pl.harvestubezpieczenia.harvestapp.domain.services.GenericService;

import java.util.List;

@RestController
@RequestMapping("agent")
public class AgentController {

    private final GenericService<Agent, AgentDto> genericService;

    public AgentController(GenericService<Agent, AgentDto> genericService) {
        this.genericService = genericService;
    }

    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllItems() {
        return genericService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getItemByID(@PathVariable int id){
        return genericService.getItemByID(id);
    }

    @PostMapping
    public ResponseEntity<String> addItem(@RequestBody AgentDto dto) {
        return genericService.addItem(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeItemById(@PathVariable int id){
        return genericService.removeItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@RequestBody AgentDto dto, @PathVariable int id){
        return genericService.updateItem(dto, id);
    }
}
