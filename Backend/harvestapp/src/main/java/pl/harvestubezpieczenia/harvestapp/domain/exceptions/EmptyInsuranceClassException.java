package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyInsuranceClassException extends DictionaryException {

    public EmptyInsuranceClassException() {
        super("'grupa ministerialna' cannot be empty");
    }
}
