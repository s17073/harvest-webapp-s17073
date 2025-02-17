package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyCropVarietyNameException extends DictionaryException {
    public EmptyCropVarietyNameException() {
        super("Crop Variety Name cannot be empty");
    }
}