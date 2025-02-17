package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptySoilClassNameException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.SoilClassName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SoilClassNameTests {
    @Test
    void SoilClassName_WhenCalledWithNullString_ThenThrowEmptyEmptySoilClassNameException(){
        assertThrows(EmptySoilClassNameException.class, () -> new SoilClassName(null));
    }

    @Test
    void SoilClassName_WhenCalledWithEmptyString_ThrowEmptyEmptySoilClassNameException() {
        assertThrows(EmptySoilClassNameException.class, () -> new SoilClassName(""));
    }
}

