package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class CropKindDTO implements GenericDTO{

    private String nazwaUprawy;
    private String taryfa;
    private boolean czyAktywna;
    private double wartoscRynkowa;
    private double wartoscMax;

}
