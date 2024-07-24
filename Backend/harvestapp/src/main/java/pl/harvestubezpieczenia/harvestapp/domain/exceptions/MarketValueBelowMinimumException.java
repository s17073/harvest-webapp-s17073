package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class MarketValueBelowMinimumException extends DictionaryException{
    public MarketValueBelowMinimumException(double marketValue) {
        super("The value of 'wartoscRynkowa' (" + marketValue + ") cannot be less than 100.");
    }
}
