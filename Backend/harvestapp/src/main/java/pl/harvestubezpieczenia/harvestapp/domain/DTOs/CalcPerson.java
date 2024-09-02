package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.sql.Date;

@Data
public class CalcPerson {

    private String imie;
    private String nazwisko;
    private String pesel;
    private Date dataUrodzenia;
    private String adresEmail;
    private String teryt;
    private String kodPocztowy;
    private String miejscowosc;
    private String ulica;
    private String numerDomu;
    private String numerMieszkania;

}
