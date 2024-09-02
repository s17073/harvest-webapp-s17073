package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class CalcLandDto {

    private String teryt;
    private String numerDzialki;
    private String obreb;
    private String kodObrebu;
    private boolean czyPoprawna;

}
