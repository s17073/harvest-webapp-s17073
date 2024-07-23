package pl.harvestubezpieczenia.harvestapp.domain.model;

import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

public interface GenericCrudModel {

    String getName();
    int getId();

    void setDataModyfikacji(ModificationDate modificationDate);
    ModificationDate getDataModyfikacji();

}
