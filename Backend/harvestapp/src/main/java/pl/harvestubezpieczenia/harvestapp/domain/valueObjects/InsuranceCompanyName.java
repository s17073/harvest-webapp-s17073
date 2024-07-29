package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

//TODO
// Add validation rules

@Embeddable
public record InsuranceCompanyName(String nazwa){

    public InsuranceCompanyName {
        if(nazwa == null)
            throw new EmptyCropKindNameException();
        if(nazwa.trim().isEmpty())
            throw new EmptyCropKindNameException();
    }

}
