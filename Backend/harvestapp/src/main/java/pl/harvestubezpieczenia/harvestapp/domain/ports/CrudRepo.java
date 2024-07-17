package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;

import java.util.List;

public interface CrudRepo<T> {

    List<T> getAllCropKinds();

    void addCropKind(T t);

    void removeCropKind(T t);

}
