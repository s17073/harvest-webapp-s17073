package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Season(String taryfa) {

    public Season {
        if(taryfa == null || taryfa.isEmpty()){
            throw new IllegalArgumentException("Season cannot be null or empty");
        } if (!(taryfa.equalsIgnoreCase("WIOSNA") || taryfa.equalsIgnoreCase("ZIMA"))) {
            throw new IllegalArgumentException("The season must be one of the following values: WIOSNA, ZIMA.");
        }

        taryfa = taryfa.toUpperCase();
    }
}
