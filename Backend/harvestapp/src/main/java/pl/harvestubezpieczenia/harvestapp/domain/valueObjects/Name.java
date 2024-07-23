package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Name(String nazwaUprawy) {

    public Name{
        if(nazwaUprawy.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

}
