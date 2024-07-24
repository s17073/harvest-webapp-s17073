package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyAdditionDateException extends DictionaryException{
    public EmptyAdditionDateException() {
        super("Addition date cannot be empty");
    }
}
