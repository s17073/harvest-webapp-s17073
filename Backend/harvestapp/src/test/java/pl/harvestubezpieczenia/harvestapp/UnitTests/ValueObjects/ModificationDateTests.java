package pl.harvestubezpieczenia.harvestapp.UnitTests.ValueObjects;

import org.junit.jupiter.api.Test;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.AdditionDateInFutureException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.DeletionDateBeforeAdditionDateException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.DeletionDateInFutureException;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class ModificationDateTests {

    @Test
    void ModificationDate_WhenCalledWithoutParams_CreateNewModificationDate(){

        //Act
        ModificationDate modificationDate = new ModificationDate();

        //Assert
        assertTrue((Timestamp.valueOf("2024-01-01 00:00:01").getTime() - Timestamp.valueOf("2024-01-01 00:00:00").getTime()) <= 1000);
        assertTrue((System.currentTimeMillis() - modificationDate.dataDodania().getTime()) <= 1000);
        assertNull(modificationDate.dataUsuniecia());
    }

    @Test
    void ModificationDate_WhenCalledWitValidAddDate_CreateNewModificationDate(){
        //Arrange
        Timestamp additionDate = new Timestamp(System.currentTimeMillis());

        //Act
        ModificationDate modificationDate = new ModificationDate(additionDate);

        //Assert
        assertEquals(additionDate, modificationDate.dataDodania());
        assertTrue((Timestamp.valueOf("2024-01-01 00:00:01").getTime() - Timestamp.valueOf("2024-01-01 00:00:00").getTime()) <= 1000);
        assertTrue((System.currentTimeMillis() - modificationDate.dataUsuniecia().getTime()) <= 1000);
    }

    @Test
    void ModificationDate_WhenCalledWitFutureAddDate_ThrowAdditionDateInFutureException(){
        //Arrange
        Timestamp additionDate = new Timestamp(System.currentTimeMillis() + 10000);

        //Assert
        assertThrows(AdditionDateInFutureException.class, () -> new ModificationDate(additionDate, null));

    }

    @Test
    void ModificationDate_WhenCalledWitFutureDelDate_ThrowDeletionDateInFutureException(){
        //Arrange
        Timestamp additionDate = new Timestamp(System.currentTimeMillis());
        Timestamp deletionDate = new Timestamp(System.currentTimeMillis() + 10000);

        //Assert
        assertThrows(DeletionDateInFutureException.class, () -> new ModificationDate(additionDate, deletionDate));

    }

    @Test
    void ModificationDate_WhenDelDateBeforeAdDate_ThrowDeletionDateBeforeAdditionDateException(){
        //Arrange
        Timestamp additionDate = new Timestamp(System.currentTimeMillis() + 10000);
        Timestamp deletionDate = new Timestamp(System.currentTimeMillis());

        //Assert
        assertThrows(DeletionDateBeforeAdditionDateException.class, () -> new ModificationDate(additionDate, deletionDate));
    }



}
