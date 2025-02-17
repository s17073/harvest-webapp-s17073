package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropVarietyNameException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropVarietyName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoverVarietyNameTests {

    @Test
    void CoverVarietyName_WhenCalledWithNullString_ThenThrowEmptyCropKindNameException() {
        assertThrows(EmptyCropVarietyNameException.class, () -> new CropVarietyName(null));
    }

    @Test
    void CoverVarietyName_WhenCalledWithEmptyString_ThrowEmptyCropKindNameException() {
        assertThrows(EmptyCropVarietyNameException.class, () -> new CropVarietyName(""));
    }

}
