package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.GenericDTO;
import pl.harvestubezpieczenia.harvestapp.domain.Mappers.GenericMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.GenericCrudModel;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GenericService<E extends GenericCrudModel, D extends GenericDTO> {

    private final Map<String, GenericCrudRepo<E>> genericCrudRepos;
    private final Map<String, GenericMapper<E, D>> genericMappers;

    @Autowired
    public GenericService(Map<String, GenericCrudRepo<E>> genericCrudRepos, Map<String, GenericMapper<E, D>> genericMappers) {
        this.genericCrudRepos = genericCrudRepos;
        this.genericMappers = genericMappers;
    }

    protected GenericMapper<E, D> getMapper(String mapperType) {
        GenericMapper<E, D> mapper = genericMappers.get(mapperType);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for type: " + mapperType);
        }

        return mapper;
    }

    protected GenericCrudRepo<E> getRepo(String repoType) {
        GenericCrudRepo<E> repo = genericCrudRepos.get(repoType);
        if(repo == null) {
            throw new IllegalArgumentException("No repository found for type: " + repoType);
        }
        return repo;
    }

    @Transactional
    public ResponseEntity<List<D>> getAllItems(String mapperType, String repoType) {
        GenericCrudRepo<E> repo = getRepo(repoType);
        GenericMapper<E, D> mapper = getMapper(mapperType);
        List<D> activeItemsDto = new ArrayList<>();

        for(E e: repo.getAllItems()){
            if (e.getDataModyfikacji().dataUsuniecia() == null) activeItemsDto.add(mapper.mapToDto(e));
        }

        if(activeItemsDto.isEmpty()) return new ResponseEntity<>(activeItemsDto, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(activeItemsDto, HttpStatus.OK);
    }

    public ResponseEntity<D> getItemByID(String mapperType, String repoType, int id) {
        GenericCrudRepo<E> repo = getRepo(repoType);
        GenericMapper<E, D> mapper = getMapper(mapperType);

        for(E e: repo.getAllItems()){
            if(e.getId() == id){
                return new ResponseEntity<>(mapper.mapToDto(e), HttpStatus.OK);
            }
            break;
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> addItem(String mapperType, String repoType, D dto) {
        GenericCrudRepo<E> repo = getRepo(repoType);
        GenericMapper<E, D> mapper = getMapper(mapperType);

        E ItemToAdd = mapper.mapToEntity(dto);

        repo.addItem(ItemToAdd);
        return new ResponseEntity<>(ItemToAdd.getName() + " successfully added to the database.", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeItemById(String repoType, int id) {
        GenericCrudRepo<E> repo = getRepo(repoType);
        String NameOfRemovedItem;
        E itemToRemove = null;

        for(E e: repo.getAllItems()){
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

        repo.addItem(itemToRemove);

        return new ResponseEntity<>(NameOfRemovedItem + " successfully deleted from the database", HttpStatus.OK);
    }

    public ResponseEntity<String> updateItem(String mapperType, String repoType, D dto, int id) {
        GenericCrudRepo<E> repo = getRepo(repoType);
        GenericMapper<E, D> mapper = getMapper(mapperType);
        String NameOfRemovedItem;
        E itemToUpdate = null;

        for(E i: repo.getAllItems()){
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

        E itemToAdd = mapper.mapToEntity(dto);

        itemToUpdate.setDataModyfikacji(new ModificationDate(itemToUpdate.getDataModyfikacji().dataDodania()));
        repo.addItem(itemToUpdate);
        repo.addItem(itemToAdd);

        return new ResponseEntity<>(NameOfRemovedItem + " successfully updated.", HttpStatus.OK);
    }
}
