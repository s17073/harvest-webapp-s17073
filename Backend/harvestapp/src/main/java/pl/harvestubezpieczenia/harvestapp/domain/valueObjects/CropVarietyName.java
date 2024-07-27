package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

@Embeddable
public record CropVarietyName(String nazwaGatunku) {

    public CropVarietyName{
        if(nazwaGatunku == null)
            throw new EmptyCropKindNameException();
        if(nazwaGatunku.trim().isEmpty())
            throw new EmptyCropKindNameException();
    }

}
