package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pl.harvestubezpieczenia.harvestapp.domain.exceptions.MarketValueBelowMinimumException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.MaxValueLowerThanMarketValueException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;

import static org.junit.jupiter.api.Assertions.*;

public class InsuredValueTests {

    @ParameterizedTest
    @CsvSource({
            "100, 120",
            "500, 600",
            "1000, 1200"
    })
    void InsuredValue_WhenCalledWithValidParam_CreateNewInsuredValue(double marketValue, double expectedValue){

        //Act
            InsuredValue insuredValue = new InsuredValue(marketValue);
        //Assert
            assertEquals(insuredValue.wartoscRynkowa(), marketValue);
            assertEquals(insuredValue.wartoscMax(), expectedValue);

    }
    @ParameterizedTest
    @CsvSource({
            "100, 100",
            "100, 120",
            "1000, 1200",
            "100, 10000"
    })
    void InsuredValue_WhenCalledWithValidParams_CreateNewInsuredValue(double marketValue, double maxValue){
        //Act
        InsuredValue insuredValue = new InsuredValue(marketValue, maxValue);
        //Assert
        assertEquals(insuredValue.wartoscRynkowa(), marketValue);
        assertEquals(insuredValue.wartoscMax(), maxValue);
    }

    @Test
    void InsuredValue_WhenCalledMarketValueBelowMaxValue_ThrowMaxValueLowerThanMarketValueException(){

        double marketValue = 1000;
        double maxValue = 100;

        //Assert
        assertThrows(MaxValueLowerThanMarketValueException.class, () -> new InsuredValue(marketValue, maxValue));
    }

    @ParameterizedTest
    @CsvSource({
      "-0.5, 1000",
      "0, 120",
      "-100, 100"
    })
    void InsuredValue_WhenCalledMarketValueBelowMinimum_ThrowMarketValueBelowMinimumException(double marketValue, double maxValue){

        //Assert
        assertThrows(MarketValueBelowMinimumException.class, () -> new InsuredValue(marketValue, maxValue));

    }





}
