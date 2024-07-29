package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.AddressDto;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.AddressMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;
import pl.harvestubezpieczenia.harvestapp.domain.ports.AddressRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepo repo;
    private final AddressMapper mapper;


    public AddressService(AddressRepo repo, AddressMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public ResponseEntity<List<AddressDto>> getAllItems() {
        List<AddressDto> dto = new ArrayList<>();

        for(Address entity: repo.getAllItems()){
            dto.add(mapper.mapToDto(entity));
        }

        if(dto.isEmpty()) return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    public ResponseEntity<AddressDto> getItemById(int id) {
        for(Address entity: repo.getAllItems()){
            if (entity.getId() == id) return new ResponseEntity<>(mapper.mapToDto(entity), HttpStatus.OK);
            break;
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
