package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class LivestockKindDTO implements GenericDTO {

    private String nazwaZwierzecia;
    private String taryfa;
    private boolean czyAktywna;
    private double wartoscRynkowa;
    private double wartoscMax;

}
