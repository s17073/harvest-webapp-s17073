package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.harvestubezpieczenia.harvestapp.domain.ports.GenericCrudRepo;

import java.util.List;

@NoRepositoryBean
public interface GenericCrudRepoJpa<T, ID> extends JpaRepository<T, ID>, GenericCrudRepo<T> {

    default List<T> getAllItems(){
        return findAll();
    }

    default void addItem(T t){
        save(t);
    }

}
