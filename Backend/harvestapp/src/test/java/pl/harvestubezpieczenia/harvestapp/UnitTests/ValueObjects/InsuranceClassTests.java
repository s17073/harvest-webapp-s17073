package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyInsuranceClassException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.InvalidInsuranceClassException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuranceClass;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceClassTests {
    @Test
    void InsuranceClass_WhenCalledWithNullString_ThrowEmptyInsuranceClassException(){
        assertThrows(EmptyInsuranceClassException.class, () -> new InsuranceClass(null));
    }
    @Test
    void InsuranceClass_WhenCalledWithEmptyString_ThrowEmptyInsuranceClassException(){
        assertThrows(EmptyInsuranceClassException.class, () -> new InsuranceClass(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bbbb", "adasdasdfj"})
    void InsuranceClass_WhenCalledAboveLengthTwo_ThrowInvalidInsuranceClassException(String grupaMinisterialna){
        assertThrows(InvalidInsuranceClassException.class, () -> new InsuranceClass(grupaMinisterialna));
    }

    @Test
    void InsuranceClass_WhenCalledLengthEqualsOne_AddZeroToGrupaMinisterialna(){
        //Arrange
        String insuranceName = "1";
        //Act
        InsuranceClass insuranceClass = new InsuranceClass(insuranceName);
        //Assert
        assertNotNull(insuranceClass);
        assertEquals("0" + insuranceName, insuranceClass.grupaMinisterialna());
    }

    @ParameterizedTest
    @ValueSource(strings = {"akgisu4829cjsfdjds", "akvjshdf87f9asd89723kjaadsa"})
    void InsuranceClass_WhenCalledWithGrupaMinisterialnaAbove18below0(String grupaMinisterialna){
        assertThrows(InvalidInsuranceClassException.class, () -> new InsuranceClass(grupaMinisterialna));
    }

}