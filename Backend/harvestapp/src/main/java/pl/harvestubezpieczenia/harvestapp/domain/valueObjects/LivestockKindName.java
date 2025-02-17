package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyLivestockKindNameNameException;

@Embeddable
public record LivestockKindName(String nazwa) {

    public LivestockKindName {
        if(nazwa == null)
            throw new EmptyLivestockKindNameNameException();
        if(nazwa.trim().isEmpty())
            throw new EmptyLivestockKindNameNameException();
    }

}
