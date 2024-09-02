package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class CalcCropDto {

    private int idRodzajUprawy;
    private int idGatunek;
    private int idKlasaGleby;
    private boolean czyNasienna;
    private double powierzchnia;
    private double wartosc;
    private List<Integer> ryzyka;
    private List<CalcLandDto> dzialki;

}
