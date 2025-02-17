package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyLivestockKindNameNameException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.LivestockKindName;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LivestockKindNameTests {
    @Test
        //Inny wyjatek powinien byc
    void LivestockKindName_WhenCalledWithNullString_ThenThrowEmptyLivestockKindNameNameException(){
        assertThrows(EmptyLivestockKindNameNameException.class, () -> new LivestockKindName(null));
    }

    @Test
        //Inny wyjatek powinien byc
    void LivestockKindName_WhenCalledWithEmptyString_ThrowEmptyLivestockKindNameNameException() {
        assertThrows(EmptyLivestockKindNameNameException.class, () -> new LivestockKindName(""));
    }
}
