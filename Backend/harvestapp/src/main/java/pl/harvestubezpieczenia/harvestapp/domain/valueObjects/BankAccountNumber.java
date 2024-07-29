package pl.harvestubezpieczenia.harvestapp.domain.valueObjects;

import jakarta.persistence.Embeddable;

//TODO
// Add validation rules

@Embeddable
public record BankAccountNumber(String numerKonta) {

    public BankAccountNumber {

    }

}
