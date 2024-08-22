package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class AddressDto {

    private String kodPocztowy;
    private String miejscowosc;
    private String ulica;
    private String numerDomu;
    private String numerMieszkania;

//    private Teryt teryt;

}
