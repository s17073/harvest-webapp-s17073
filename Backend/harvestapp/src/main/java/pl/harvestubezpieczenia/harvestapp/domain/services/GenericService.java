package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.GenericCrudModel;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.List;

@Service
public class GenericService<T extends GenericCrudModel> {

    private final GenericCrudRepo<T> genericCrudRepo;

    @Autowired
    public GenericService(GenericCrudRepo<T> genericCrudRepo) {
        this.genericCrudRepo = genericCrudRepo;
    }

    public ResponseEntity<List<T>> getAllItems() {
        return new ResponseEntity<>(genericCrudRepo.getAllItems(), HttpStatus.OK);
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
        boolean deleted = false;
        String NameOfDeleted = "";
        for(T t: genericCrudRepo.getAllItems()){
            if(t.getId() == id) {
                NameOfDeleted = t.getName();
                genericCrudRepo.removeItemById(t);
                deleted = true;
            }
        }
        if(deleted){
            return new ResponseEntity<>(NameOfDeleted + " successfully deleted from the database", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("crop kind id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

}
