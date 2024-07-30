package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class LivestockKindDto implements GenericDto {

    private String nazwaZwierzecia;
    private String taryfa;
    private boolean czyAktywna;
    private double wartoscRynkowa;
    private double wartoscMax;

}
