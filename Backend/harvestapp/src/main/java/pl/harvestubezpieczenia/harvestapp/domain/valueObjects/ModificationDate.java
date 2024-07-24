package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

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
            throw new IllegalArgumentException("'dataDodania' cannot be null");

        if(dataUsuniecia == null){
            if(dataDodania.after(new Timestamp(System.currentTimeMillis())))
                throw new IllegalArgumentException("'dataDodania' cannot be after current Timestamp");
        } else{
            if(dataUsuniecia.before(dataDodania))
                throw new IllegalArgumentException("'dataUsuniecia' cannot be before 'dataDodania'");
        }
    }

}
