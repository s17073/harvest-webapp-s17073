package pl.harvestubezpieczenia.harvestapp.domain.ports;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GenericCrudRepo<T> {

    List<T> getAllItems();

    void addItem(T t);

}