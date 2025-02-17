package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyQuestionException;

@Embeddable
public record Question(String pytanie) {

    public Question{
        if(pytanie == null)
            throw new EmptyQuestionException();
        if(pytanie.trim().isEmpty())
            throw new EmptyQuestionException();
    }
}
