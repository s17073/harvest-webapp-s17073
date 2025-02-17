package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptySoilClassNameException extends DictionaryException{
    public EmptySoilClassNameException()  {
        super("Soil Name cannot be null or empty");
    }
}
