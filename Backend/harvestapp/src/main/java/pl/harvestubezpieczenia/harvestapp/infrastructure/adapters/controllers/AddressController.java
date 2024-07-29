package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AddressDto;
import pl.harvestubezpieczenia.harvestapp.domain.services.AddressService;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getItemById(@PathVariable int id){
        return service.getItemById(id);
    }


}
