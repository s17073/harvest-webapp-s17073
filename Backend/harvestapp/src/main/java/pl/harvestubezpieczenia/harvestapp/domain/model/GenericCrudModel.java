package pl.harvestubezpieczenia.harvestapp.domain.model;


import java.sql.Timestamp;

public interface GenericCrudModel {

    String getName();
    int getId();
    void setDataUsuniecia(Timestamp dataUsuniecia);
    Timestamp getDataUsuniecia();

}
