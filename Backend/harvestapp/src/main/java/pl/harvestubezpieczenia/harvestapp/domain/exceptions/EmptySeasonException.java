package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptySeasonException extends DictionaryException{
    public EmptySeasonException() {
        super("Season cannot be null or empty");
    }
}
