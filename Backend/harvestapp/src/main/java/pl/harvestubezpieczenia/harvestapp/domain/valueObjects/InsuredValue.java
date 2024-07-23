package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record InsuredValue(double wartoscRynkowa, double wartoscMax) {

    public InsuredValue(double wartoscRynkowa){
        this(wartoscRynkowa, wartoscRynkowa * 1.2);
    }

    public InsuredValue{
        if(wartoscRynkowa > wartoscMax && wartoscMax > 0) {
            throw new IllegalArgumentException("The maximum value (" + wartoscMax + ") cannot be lower than the market value (" + wartoscRynkowa + ")");
        }
        if(wartoscRynkowa < 0 || wartoscMax < 0) {
            throw new IllegalArgumentException("The value cannot be less than 0.");
        }
    }

}