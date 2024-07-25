package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class DeletionDateInFutureException extends DictionaryException {
    public DeletionDateInFutureException() {
        super("DeletionDate cannot be after current Timestamp");
    }
}
