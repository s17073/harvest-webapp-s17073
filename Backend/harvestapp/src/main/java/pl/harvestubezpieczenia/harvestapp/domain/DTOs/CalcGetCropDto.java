package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CalcGetCropDto {

    private int id;
    private int idUprawy;
    private int idGatunek;
    private int idKlasaGleby;
    private String uprawa;
    private String gatunek;
    private String klasaGleby;
    private boolean czyNasienna;
    private double powierzchnia;
    private double wartosc;
    private double wartoscRynkowa;
    private double wartoscMax;
    private double sumaUbezpieczenia;
    private List<Integer> ryzyka;
    private List<CalcLandCropDto> dzialki;

}
