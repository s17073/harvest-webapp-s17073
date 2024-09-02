package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CalcLivestockDto {

    private int id;
    private int idRodzajZwierzecia;
    private int liczba;
    private boolean naMieso;
    private String nazwaZwierzecia;
    private List<Integer> ryzyka;
    private int sumaUbezpieczenia;
    private double wartosc;
    private double wartoscRynkowa;

}
