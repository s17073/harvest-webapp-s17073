package pl.harvestubezpieczenia.harvestapp.domain.model;

import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

public interface GenericCrudModel {

    int getId();
    String getName();
    void setDataModyfikacji(ModificationDate modificationDate);
    ModificationDate getDataModyfikacji();

}
