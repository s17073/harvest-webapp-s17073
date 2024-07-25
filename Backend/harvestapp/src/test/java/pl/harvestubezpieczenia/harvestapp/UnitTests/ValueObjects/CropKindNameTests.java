package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropKindName;

import static org.junit.jupiter.api.Assertions.*;

public class CropKindNameTests {

    @Test
    void CropKindName_WhenCalledWithValidString_CreateNewCropKindName(){
        //Arrange
            String cropName = "rzepak";
        //Act
            CropKindName cropKindName = new CropKindName(cropName);
        //Assert
            assertNotNull(cropKindName);
            assertEquals(cropName, cropKindName.nazwaUprawy());
    }

    @Test
    void CropKindName_WhenCalledNull_ThrowEmptyCropKindNameException(){
        //Arrange
        String cropName = null;

        //Assert
        assertThrows(EmptyCropKindNameException.class, () ->  new CropKindName(cropName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void CropKindName_WhenCalledEmptyString_ThrowEmptyCropKindNameException(String cropName){

        //Assert
        assertThrows(EmptyCropKindNameException.class, () ->  new CropKindName(cropName));

    }

}
