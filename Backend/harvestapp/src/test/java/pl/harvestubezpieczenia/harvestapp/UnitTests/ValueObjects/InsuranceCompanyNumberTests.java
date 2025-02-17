package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;


import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyInsuranceCompanyNumberException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuranceCompanyNumber;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsuranceCompanyNumberTests {
    @Test
        //Inny wyjatek powinien byc
    void InsuranceCompanyNumber_WhenCalledWithNullString_ThenThrowEmptyInsuranceCompanyNumberVarietyNameException(){
        assertThrows(EmptyInsuranceCompanyNumberException.class, () -> new InsuranceCompanyNumber(null));
    }

    @Test
        //Inny wyjatek powinien byc
    void InsuranceCompanyNumber_WhenCalledWithEmptyString_ThrowEmptyInsuranceCompanyNumberVarietyNameException() {
        assertThrows(EmptyInsuranceCompanyNumberException.class, () -> new InsuranceCompanyNumber(""));
    }
}
