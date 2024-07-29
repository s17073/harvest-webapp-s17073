package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

//TODO
// Add validation rules

@Embeddable
public record AddressLocalization(String kodPocztowy, String miejscowosc, String ulica, String numerDomu, String numerMieszkania) {

    public AddressLocalization {

    }

}
