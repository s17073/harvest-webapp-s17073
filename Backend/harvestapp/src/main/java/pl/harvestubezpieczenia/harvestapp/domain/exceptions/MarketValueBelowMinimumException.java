package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class MarketValueBelowMinimumException extends DictionaryException{
    public MarketValueBelowMinimumException(double marketValue) {
        super("The value of 'wartoscRynkowa' (" + marketValue + ") must be greater than or equal to 0.");
    }
}
