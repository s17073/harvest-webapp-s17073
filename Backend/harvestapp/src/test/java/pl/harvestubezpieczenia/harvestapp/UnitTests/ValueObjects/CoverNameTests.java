package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyCropKindNameException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CoverName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoverNameTests {

    @Test
    void CoverName_WhenCalledWithNullString_ThrowEmptyCropKindNameException() {
        assertThrows(EmptyCropKindNameException.class, () -> new CoverName(null));
    }

    @Test
    void CoverName_WhenCalledWithEmptyString_ThrowEmptyCropKindNameException() {
        assertThrows(EmptyCropKindNameException.class, () -> new CoverName(""));
    }

}