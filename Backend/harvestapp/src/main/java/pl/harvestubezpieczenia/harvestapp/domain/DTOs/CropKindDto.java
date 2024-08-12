package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CropKindDto implements GenericDto {

    private int id;
    private String nazwaUprawy;
    private String taryfa;
    private boolean czyAktywna;
    private double wartoscRynkowa;
    private double wartoscMax;

    private List<String> gatunki = new ArrayList<>();

}
