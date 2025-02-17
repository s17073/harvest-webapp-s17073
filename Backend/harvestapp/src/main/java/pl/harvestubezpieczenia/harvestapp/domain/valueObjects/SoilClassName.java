package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptySoilClassNameException;

@Embeddable
public record SoilClassName(String klasaGleby) {

    public SoilClassName {
        if(klasaGleby == null)
            throw new EmptySoilClassNameException();
        if(klasaGleby.trim().isEmpty())
            throw new EmptySoilClassNameException();
    }

}
