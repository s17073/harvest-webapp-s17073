package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record InsuredValue(double wartoscRynkowa, double wartoscMax) {

    public InsuredValue(double wartoscRynkowa){
        this(wartoscRynkowa, wartoscRynkowa * 1.2);
    }

    public InsuredValue{
        if(wartoscRynkowa > wartoscMax && wartoscMax > 0){
            throw new IllegalArgumentException("wartoscRynkowa: " + wartoscRynkowa + " > wartoscMax: " + wartoscMax);
        }
    }

}