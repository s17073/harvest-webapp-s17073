package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record InsuredValue(double wartoscRynkowa, double wartoscMax) {

    public InsuredValue(double wartoscRynkowa){
        this(wartoscRynkowa, Math.round(wartoscRynkowa * 1.2));
    }

    public InsuredValue{
        if(wartoscRynkowa > wartoscMax && wartoscMax > 0)
            throw new IllegalArgumentException("The maximum value (" + wartoscMax + ") cannot be lower than the market value (" + wartoscRynkowa + ")");
        if(wartoscRynkowa < 100)
            throw new IllegalArgumentException("The value of 'wartoscRynkowa' (" + wartoscRynkowa + ") cannot be less than 100.");
        if(wartoscMax < 120)
            throw new IllegalArgumentException("The value of 'wartoscMax' (" + wartoscMax + ") cannot be less than 120.");
    }

}