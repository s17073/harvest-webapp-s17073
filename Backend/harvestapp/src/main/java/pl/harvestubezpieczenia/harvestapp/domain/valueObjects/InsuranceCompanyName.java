package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyInsuranceCompanyNameException;

@Embeddable
public record InsuranceCompanyName(String nazwa){

    public InsuranceCompanyName {
        if(nazwa == null)
            throw new EmptyInsuranceCompanyNameException();
        if(nazwa.trim().isEmpty())
            throw new EmptyInsuranceCompanyNameException();
    }

}
