package pl.harvestubezpieczenia.harvestapp.domain.ports;

import java.util.List;

public interface CrudRepo<T> {

    List<T> getAllCropKinds();

    void addCropKind(T t);

    void removeCropKind(T t);

}
