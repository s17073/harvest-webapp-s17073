package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

@Embeddable
public record CoverName(String nazwa) {

    public CoverName{
        if(nazwa == null)
            throw new EmptyCropKindNameException();
        if(nazwa.trim().isEmpty())
            throw new EmptyCropKindNameException();
    }
}
