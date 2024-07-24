package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptySeasonException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.InvalidSeasonException;

@Embeddable
public record Season(String taryfa) {

    public Season {
        if(taryfa == null || taryfa.isEmpty()){
            throw new EmptySeasonException();
        } if (!(taryfa.equalsIgnoreCase("WIOSNA") || taryfa.equalsIgnoreCase("ZIMA"))) {
            throw new InvalidSeasonException();
        }

        taryfa = taryfa.toUpperCase();
    }
}
