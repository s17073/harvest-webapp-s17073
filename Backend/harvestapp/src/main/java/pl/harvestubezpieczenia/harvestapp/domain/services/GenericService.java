package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.GenericDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.GenericCrudModel;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.util.ArrayList;
import java.util.List;

public class GenericService<E extends GenericCrudModel, D extends GenericDto> {

    private final GenericCrudRepo<E> repo;
    private final GenericMapper<E, D> map;

    public GenericService(GenericCrudRepo<E> repo, GenericMapper<E, D> map) {
        this.repo = repo;
        this.map = map;
    }

    @Transactional
    public ResponseEntity<List<D>> getAllItems() {
        List<D> activeItemsDto = new ArrayList<>();

        for(E e: repo.getAllItems()){
            if (e.getDataModyfikacji().dataUsuniecia() == null) activeItemsDto.add(map.mapToDto(e));
        }

        if(activeItemsDto.isEmpty()) return new ResponseEntity<>(activeItemsDto, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(activeItemsDto, HttpStatus.OK);
    }

    public ResponseEntity<D> getItemByID(int id) {
        for(E e: repo.getAllItems()){
            if(e.getId() == id){
                return new ResponseEntity<>(map.mapToDto(e), HttpStatus.OK);
            }
            break;
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addItem(D dto) {
        E ItemToAdd = map.mapToEntity(dto);

        repo.addItem(ItemToAdd);
        return new ResponseEntity<>(ItemToAdd.getName() + " successfully added to the database.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeItemById(int id) {
        String NameOfRemovedItem;
        E itemToRemove = findForModify(id);

        if(itemToRemove == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToRemove.getDataModyfikacji().dataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }

        NameOfRemovedItem = itemToRemove.getName();
        itemToRemove.setDataModyfikacji(new ModificationDate(itemToRemove.getDataModyfikacji().dataDodania()));

        repo.addItem(itemToRemove);

        return new ResponseEntity<>(NameOfRemovedItem + " successfully deleted from the database", HttpStatus.OK);
    }

    public ResponseEntity<String> updateItem(D dto, int id) {
        String NameOfRemovedItem;
        E itemToUpdate = findForModify(id);

        if(itemToUpdate == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToUpdate.getDataModyfikacji().dataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }

        NameOfRemovedItem = itemToUpdate.getName();

        E itemToAdd = map.mapToEntity(dto);

        itemToUpdate.setDataModyfikacji(new ModificationDate(itemToUpdate.getDataModyfikacji().dataDodania()));
        repo.addItem(itemToUpdate);
        repo.addItem(itemToAdd);

        return new ResponseEntity<>(NameOfRemovedItem + " successfully updated.", HttpStatus.OK);
    }

    private E findForModify(int id){
        for(E e: repo.getAllItems()){
            if(e.getId() == id) {
                return e;
            }
            break;
        }
        return null;
    }
}
