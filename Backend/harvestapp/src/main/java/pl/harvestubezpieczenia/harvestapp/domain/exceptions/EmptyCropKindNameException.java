package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyCropKindNameException extends DictionaryException{

    public EmptyCropKindNameException() {
        super("'nazwaUprawy' cannot be empty");
    }

}
