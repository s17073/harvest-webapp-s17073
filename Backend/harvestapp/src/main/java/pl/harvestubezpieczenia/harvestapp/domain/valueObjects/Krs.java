package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

//TODO
// Add validation rules

@Embeddable
public record Krs(String krs) {

    public Krs{

    }
}
