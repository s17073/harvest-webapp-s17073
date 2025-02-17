package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyInsuranceCompanyNameException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuranceCompanyName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InsuranceCompanyNameTests {
    @Test
    void InsuranceCompanyName_WhenCalledWithNullString_ThenThrowEmptyEmptyInsuranceCompanyNameException(){
        assertThrows(EmptyInsuranceCompanyNameException.class, () -> new InsuranceCompanyName(null));
    }

    @Test
    void InsuranceCompanyName_WhenCalledWithEmptyString_EmptyInsuranceCompanyNameException() {
        assertThrows(EmptyInsuranceCompanyNameException.class, () -> new InsuranceCompanyName(""));
    }
}