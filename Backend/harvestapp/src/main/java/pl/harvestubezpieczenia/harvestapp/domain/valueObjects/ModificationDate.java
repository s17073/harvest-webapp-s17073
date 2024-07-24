package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.AdditionDateInFutureException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.DeletionDateBeforeAdditionDateException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptyAdditionDateException;

import java.sql.Timestamp;

@Embeddable
public record ModificationDate(Timestamp dataDodania, Timestamp dataUsuniecia) {

    public ModificationDate(){
        this(new Timestamp(System.currentTimeMillis()), null);
    }

    public ModificationDate(Timestamp dataDodania) {
        this(dataDodania, new Timestamp(System.currentTimeMillis()));
    }

    public ModificationDate{
        if(dataDodania == null)
            throw new EmptyAdditionDateException();

        if(dataUsuniecia == null){
            if(dataDodania.after(new Timestamp(System.currentTimeMillis())))
                throw new AdditionDateInFutureException();
        } else{
            if(dataUsuniecia.before(dataDodania))
                throw new DeletionDateBeforeAdditionDateException();
        }
    }

}
