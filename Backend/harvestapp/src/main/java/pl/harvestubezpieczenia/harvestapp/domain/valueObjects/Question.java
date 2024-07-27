package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

@Embeddable
public record Question(String pytanie) {

    public Question{
        if(pytanie == null)
            throw new EmptyCropKindNameException();
        if(pytanie.trim().isEmpty())
            throw new EmptyCropKindNameException();
    }
}
