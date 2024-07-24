package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class AdditionDateInFutureException extends DictionaryException{
    public AdditionDateInFutureException() {
        super("AdditionDate cannot be after current Timestamp");
    }
}
