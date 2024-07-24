package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class DeletionDateBeforeAdditionDateException extends DictionaryException{
    public DeletionDateBeforeAdditionDateException() {
        super("DeletionDate cannot be before addition date");
    }
}
