package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class InvalidInsuranceClassException extends DictionaryException{
    public InvalidInsuranceClassException() {
        super("'grupa ministerialna' must be a numeric value between 1 and 18.");
    }
}
