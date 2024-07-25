package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.EmptySeasonException;
import pl.harvestubezpieczenia.harvestapp.domain.exceptions.InvalidSeasonException;

@Embeddable
public record Season(String taryfa) {

    public Season {
        if(taryfa == null || taryfa.trim().isEmpty()){
            throw new EmptySeasonException();
        } if (!(taryfa.trim().equalsIgnoreCase("WIOSNA") || taryfa.trim().equalsIgnoreCase("ZIMA"))) {
            throw new InvalidSeasonException();
        }

        taryfa = taryfa.trim().toUpperCase();
    }
}
