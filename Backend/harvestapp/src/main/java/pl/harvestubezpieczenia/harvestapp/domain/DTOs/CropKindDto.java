package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.util.Set;

@Data
public class CropKindDto implements GenericDTO{

    private String nazwaUprawy;
    private String taryfa;
    private boolean czyAktywna;
    private double wartoscRynkowa;
    private double wartoscMax;

    private Set<CropVarietyDto> gatunki;

}
