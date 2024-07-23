package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CropKindDTO;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.GenericCrudModel;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenericService<E extends GenericCrudModel, D extends CropKindDTO> {

    private final GenericCrudRepo<E> genericCrudRepo;
    private final GenericMapper<E,D> genericMapper;

    @Autowired
    public GenericService(GenericCrudRepo<E> genericCrudRepo, GenericMapper<E, D> genericMapper) {
        this.genericCrudRepo = genericCrudRepo;
        this.genericMapper = genericMapper;
    }

    public ResponseEntity<List<E>> getAllItems() {
        List<E> activeItems = new ArrayList<>();

        for(E e: genericCrudRepo.getAllItems()){
            if (e.getDataModyfikacji().dataUsuniecia() == null) activeItems.add(e);
        }

        return new ResponseEntity<>(activeItems, HttpStatus.OK);
    }

    public ResponseEntity<E> getItemByID(int id) {
        for(E t: genericCrudRepo.getAllItems()){
            if(t.getId() == id){
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addItem(D dto) {

        E ItemToAdd = genericMapper.mapToEntity(dto);

        genericCrudRepo.addItem(ItemToAdd);
        return new ResponseEntity<>(ItemToAdd.getName() + " successfully added to the database.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeItemById(int id) {
        String NameOfRemovedItem;
        E itemToRemove = null;

        for(E e: genericCrudRepo.getAllItems()){
            if(e.getId() == id) {
                itemToRemove = e;
                break;
            }
        }

        if(itemToRemove == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToRemove.getDataModyfikacji().dataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }

        NameOfRemovedItem = itemToRemove.getName();
        itemToRemove.setDataModyfikacji(new ModificationDate(itemToRemove.getDataModyfikacji().dataDodania()));

        genericCrudRepo.addItem(itemToRemove);

        return new ResponseEntity<>(NameOfRemovedItem + " successfully deleted from the database", HttpStatus.OK);
    }

    public ResponseEntity<String> updateItem(D dto, int id) {
        String NameOfRemovedItem;
        E itemToUpdate = null;

        for(E i: genericCrudRepo.getAllItems()){
            if(i.getId() == id) {
                itemToUpdate = i;
                break;
            }
        }

        if(itemToUpdate == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToUpdate.getDataModyfikacji().dataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }

        NameOfRemovedItem = itemToUpdate.getName();

        E itemToAdd = genericMapper.mapToEntity(dto);

        itemToUpdate.setDataModyfikacji(new ModificationDate(itemToUpdate.getDataModyfikacji().dataDodania()));
        genericCrudRepo.addItem(itemToUpdate);
        genericCrudRepo.addItem(itemToAdd);

        return new ResponseEntity<>(NameOfRemovedItem + " successfully updated.", HttpStatus.OK);
    }
}
