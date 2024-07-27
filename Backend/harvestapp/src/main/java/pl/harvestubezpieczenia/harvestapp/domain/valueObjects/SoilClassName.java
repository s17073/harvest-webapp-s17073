package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

@Embeddable
public record SoilClassName(String klasaGleby) {

    public SoilClassName {
        if(klasaGleby == null)
            throw new EmptyCropKindNameException();
        if(klasaGleby.trim().isEmpty())
            throw new EmptyCropKindNameException();
    }

}
