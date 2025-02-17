package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyQuestionException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Question;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTests {
    @Test
    void Question_WhenCalledWithNullString_ThenThrowEmptyQuestionException(){
        assertThrows(EmptyQuestionException.class, () -> new Question(null));
    }

    @Test
    void Question_WhenCalledWithEmptyString_ThrowEmptyEmptyQuestionException() {
        assertThrows(EmptyQuestionException.class, () -> new Question(""));
    }
}

