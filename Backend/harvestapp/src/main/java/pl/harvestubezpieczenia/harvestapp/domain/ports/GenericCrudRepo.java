package pl.harvestubezpieczenia.harvestapp.domain.ports;

import java.util.List;

public interface GenericCrudRepo<T> {

    List<T> getAllItems();

    void addItem(T t);

}