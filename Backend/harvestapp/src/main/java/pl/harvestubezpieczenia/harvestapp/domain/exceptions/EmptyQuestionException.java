package pl.harvestubezpieczenia.harvestapp.domain.exceptions;

public class EmptyQuestionException extends DictionaryException{
    public EmptyQuestionException()  {
        super("Question cannot be null or empty");
    }
}