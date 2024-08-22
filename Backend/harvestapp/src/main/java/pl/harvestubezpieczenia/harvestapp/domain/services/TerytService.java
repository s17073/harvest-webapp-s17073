package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.PartOfTerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.TerytDto;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.InvalidTerytCodeException;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.TerytMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;
import pl.harvestubezpieczenia.harvestapp.domain.ports.TerytRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class TerytService {

    private final TerytRepo repo;
    private final TerytMapper mapper;

    @Autowired
    public TerytService(TerytRepo repo, TerytMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Transactional
    public ResponseEntity<List<TerytDto>> getAllItems() {
        List<TerytDto> dto = new ArrayList<>();

        for(Teryt entity: repo.getAllItems()){
            dto.add(mapper.mapToDto(entity));
        }

        if(dto.isEmpty()) return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<TerytDto> getItemById(int id) {
        for(Teryt entity: repo.getAllItems()){
            if (entity.getId() == id) return new ResponseEntity<>(mapper.mapToDto(entity), HttpStatus.OK);
            break;
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<PartOfTerytDto>> getWojewodztwa() {
        List<PartOfTerytDto> wojewodztwa = repo.getWojewodztwa();

        return new ResponseEntity<>(wojewodztwa, HttpStatus.OK);
    }

    public ResponseEntity<List<PartOfTerytDto>> getPowiaty(String teryt) {
        List<PartOfTerytDto> powiaty = repo.getPowiaty(teryt);

        return new ResponseEntity<>(powiaty, HttpStatus.OK);
    }

    public ResponseEntity<List<PartOfTerytDto>> getGminy(String teryt) {
        List<PartOfTerytDto> gminy = repo.getGminy(teryt);

        return new ResponseEntity<>(gminy, HttpStatus.OK);
    }

    public ResponseEntity<TerytDto> getTerytData(String teryt) {
        TerytDto dto = new TerytDto();
        String wojewodztwo;
        String powiat;
        String gmina;

        try {
            wojewodztwo = repo.getTerytPart(teryt.substring(0, 2));
            powiat = repo.getTerytPart(teryt.substring(0, 4));
            gmina = repo.getTerytPart(teryt);

            if(wojewodztwo==null || powiat==null || gmina==null){
                throw new InvalidTerytCodeException();
            }
        } catch(InvalidTerytCodeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        dto.setKodTeryt(teryt);
        dto.setWojewodztwo(wojewodztwo);
        dto.setPowiat(powiat);
        dto.setGmina(gmina);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
