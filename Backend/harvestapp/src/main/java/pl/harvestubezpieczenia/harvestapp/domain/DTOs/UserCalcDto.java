package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.sql.Date;

@Data
public class UserCalcDto {

    private int idKalkulacja;
    private String numerKalkulacji;
    private Date dataPoczatkuOchrony;
    private Date dataKoncaOchrony;
    private String imie;
    private String nazwisko;

}
