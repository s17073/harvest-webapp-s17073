package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyLivestockKindNameNameException extends DictionaryException {
    public EmptyLivestockKindNameNameException() {
        super("Livestock Kind Name cannot be null or empty");
    }
}
