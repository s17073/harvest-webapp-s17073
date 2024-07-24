package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.MarketValueBelowMinimumException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.MaxValueBelowMinimumException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.MaxValueLowerThanMarketValueException;

@Embeddable
public record InsuredValue(double wartoscRynkowa, double wartoscMax) {

    public InsuredValue(double wartoscRynkowa){
        this(wartoscRynkowa, Math.round(wartoscRynkowa * 1.2));
    }

    public InsuredValue{
        if(wartoscRynkowa > wartoscMax && wartoscMax > 0)
            throw new MaxValueLowerThanMarketValueException(wartoscMax, wartoscRynkowa);
        if(wartoscRynkowa < 100)
            throw new MarketValueBelowMinimumException(wartoscRynkowa);
        if(wartoscMax < 120)
            throw new MaxValueBelowMinimumException(wartoscMax);
    }

}