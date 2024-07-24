package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class InvalidSeasonException extends DictionaryException{
    public InvalidSeasonException() {
        super("The season must be one of the following values: WIOSNA, ZIMA.");
    }
}
