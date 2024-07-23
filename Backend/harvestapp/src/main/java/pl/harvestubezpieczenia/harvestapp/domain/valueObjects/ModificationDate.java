package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

import java.sql.Timestamp;

@Embeddable
public record ModificationDate(Timestamp dataDodania, Timestamp dataUsuniecia) {

    public ModificationDate(){
        this(new Timestamp(System.currentTimeMillis()), null);
    }

    public ModificationDate(Timestamp dataDodania) {
        this(dataDodania, new Timestamp(System.currentTimeMillis()));
    }

}
