package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class MaxValueBelowMinimumException extends DictionaryException {
    public MaxValueBelowMinimumException(double maxValue) {
        super("The value of 'wartoscMax' (" + maxValue + ") cannot be less than 120.");
    }
}
