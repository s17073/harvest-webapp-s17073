package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyInsuranceClassException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.InvalidInsuranceClassException;

@Embeddable
public record InsuranceClass(String grupaMinisterialna) {

    public InsuranceClass{
        if(grupaMinisterialna == null || grupaMinisterialna.trim().isEmpty()) {
            throw new EmptyInsuranceClassException();
        } else {
            if (grupaMinisterialna.length() > 2) {
                throw new InvalidInsuranceClassException();
            } else if (grupaMinisterialna.length() == 1) {
                grupaMinisterialna = "0" + grupaMinisterialna;
            }

            try {
                int grMinInt = Integer.parseInt(grupaMinisterialna);
                if (grMinInt > 18 || grMinInt < 0)
                    throw new InvalidInsuranceClassException();
            } catch (NumberFormatException e) {
                throw new InvalidInsuranceClassException();
            }
        }

        }


}
