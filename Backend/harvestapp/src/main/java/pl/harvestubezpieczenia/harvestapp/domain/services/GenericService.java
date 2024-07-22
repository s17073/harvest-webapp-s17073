package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.GenericCrudModel;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenericService<T extends GenericCrudModel> {

    private final GenericCrudRepo<T> genericCrudRepo;

    @Autowired
    public GenericService(GenericCrudRepo<T> genericCrudRepo) {
        this.genericCrudRepo = genericCrudRepo;
    }

    public ResponseEntity<List<T>> getAllItems() {
        List<T> activeItems = new ArrayList<>();

        for(T t: genericCrudRepo.getAllItems()){
            if (t.getDataUsuniecia() == null) activeItems.add(t);
        }

        return new ResponseEntity<>(activeItems, HttpStatus.OK);
    }

    public ResponseEntity<T> getItemByID(int id) {
        for(T t: genericCrudRepo.getAllItems()){
            if(t.getId() == id){
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addItem(T t) {
        genericCrudRepo.addItem(t);
        return new ResponseEntity<>(t.getName() + " successfully added to the database.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeItemById(int id) {
        String NameOfDeleted;
        T itemToUpdate = null;

        for(T t: genericCrudRepo.getAllItems()){
            if(t.getId() == id) {
                itemToUpdate = t;
                break;
            }
        }

        if(itemToUpdate == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToUpdate.getDataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }

        NameOfDeleted = itemToUpdate.getName();
        itemToUpdate.setDataUsuniecia(new Timestamp(System.currentTimeMillis()));
        genericCrudRepo.addItem(itemToUpdate);

        return new ResponseEntity<>(NameOfDeleted + " successfully deleted from the database", HttpStatus.OK);
    }

    public ResponseEntity<String> updateItem(T t, int id) {
        String NameOfDeleted;
        T itemToUpdate = null;

        for(T i: genericCrudRepo.getAllItems()){
            if(i.getId() == id) {
                itemToUpdate = i;
                break;
            }
        }

        try {
            t.getClass().getMethod("getEntityName");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        if(itemToUpdate == null){
            return new ResponseEntity<>("ID: " + id + " not found.", HttpStatus.NOT_FOUND);
        } else if (itemToUpdate.getDataUsuniecia() != null){
            return new ResponseEntity<>("ID: " + id + " has already been deleted.", HttpStatus.FORBIDDEN);
        }

        NameOfDeleted = itemToUpdate.getName();
        itemToUpdate.setDataUsuniecia(new Timestamp(System.currentTimeMillis()));
        genericCrudRepo.addItem(itemToUpdate);
        genericCrudRepo.addItem(t);

        return new ResponseEntity<>(NameOfDeleted + " successfully updated.", HttpStatus.OK);
    }
}
