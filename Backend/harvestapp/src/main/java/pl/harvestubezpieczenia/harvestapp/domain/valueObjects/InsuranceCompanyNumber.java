package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;

//TODO
// Add validation rules

@Embeddable
public record InsuranceCompanyNumber(String numerZakladu) {

    public InsuranceCompanyNumber{
        if(numerZakladu == null)
            throw new EmptyCropKindNameException();
        if(numerZakladu.trim().isEmpty())
            throw new EmptyCropKindNameException();
    }

}
