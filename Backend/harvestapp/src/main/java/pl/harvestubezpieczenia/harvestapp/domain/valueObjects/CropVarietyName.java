package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropVarietyNameException;

@Embeddable
public record CropVarietyName(String nazwaGatunku) {

    public CropVarietyName{
        if(nazwaGatunku == null)
            throw new EmptyCropVarietyNameException();
        if(nazwaGatunku.trim().isEmpty())
            throw new EmptyCropVarietyNameException();
    }

}
