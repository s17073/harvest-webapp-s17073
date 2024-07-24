package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

@Embeddable
public record CropKindName(String nazwaUprawy) {

    public CropKindName {
        if(nazwaUprawy == null)
            throw new EmptyCropKindNameException();
        if(nazwaUprawy.trim().isEmpty())
            throw new EmptyCropKindNameException();

    }

}
