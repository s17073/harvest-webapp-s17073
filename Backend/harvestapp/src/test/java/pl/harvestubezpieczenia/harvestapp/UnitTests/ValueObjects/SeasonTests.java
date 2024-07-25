package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptySeasonException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.InvalidSeasonException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

import static org.junit.jupiter.api.Assertions.*;

public class SeasonTests {

    @ParameterizedTest
    @ValueSource(strings = {"wiosna","WiOsNa","WIOSNA","zima","ZiMa","ZIMA", " wiosna ", " zima "})
    void SeasonTests_WhenCalledWithValidString_CreateNewSeason(String rate){
        //Act
        Season season = new Season(rate);
        //Assert
        assertEquals(season.taryfa(), rate.trim().toUpperCase());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void SeasonTests_WhenCalledWithEmptyString_ThrowEmptySeasonException(String rate){
        //Assert
        assertThrows(EmptySeasonException.class, () -> new Season(rate));
    }
    @ParameterizedTest
    @ValueSource(strings = {"niezima", "wiosnaa", " z i m a "})
    void SeasonTests_WhenCalledWithInvalidString_ThrowInvalidSeasonException(String rate){
        //Assert
        assertThrows(InvalidSeasonException.class, () -> new Season(rate));
    }

}
