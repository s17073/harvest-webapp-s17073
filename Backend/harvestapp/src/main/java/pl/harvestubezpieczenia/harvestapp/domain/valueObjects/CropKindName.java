package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CropKindName(String nazwaUprawy) {

    public CropKindName {
        if(nazwaUprawy.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");

    }

}
