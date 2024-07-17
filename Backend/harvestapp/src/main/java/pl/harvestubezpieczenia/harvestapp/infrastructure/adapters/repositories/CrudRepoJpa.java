package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CropKindRepo;
import pl.harvestubezpieczenia.harvestapp.domain.ports.CrudRepo;

import java.util.List;

@NoRepositoryBean
public interface CrudRepoJpa<T, ID> extends JpaRepository<T, ID>, CrudRepo<T> {

    default List<T> getAllCropKinds(){
        return findAll();
    };

    default void addCropKind(T t){
        save(t);
    }

    default void removeCropKind(T t){
        delete(t);
    }

}
