package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyInsuranceCompanyNumberException;

//TODO
// Add validation rules

@Embeddable
public record InsuranceCompanyNumber(String numerZakladu) {

    public InsuranceCompanyNumber{
        if(numerZakladu == null)
            throw new EmptyInsuranceCompanyNumberException();
        if(numerZakladu.trim().isEmpty())
            throw new EmptyInsuranceCompanyNumberException();
    }

}
