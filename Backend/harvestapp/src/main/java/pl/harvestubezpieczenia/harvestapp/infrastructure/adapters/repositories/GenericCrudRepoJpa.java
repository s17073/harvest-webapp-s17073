package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericCrudRepoJpa<T> extends JpaRepository<T, Long> {

    default List<T> getAllItems(){
        return findAll();
    }

    default void addItem(T t){
        save(t);
    }

}
