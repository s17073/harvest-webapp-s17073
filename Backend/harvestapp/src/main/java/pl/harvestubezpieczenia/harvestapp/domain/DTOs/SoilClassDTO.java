package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class SoilClassDTO implements GenericDTO{

    private String klasaGleby;
    private String opis;
    private String taryfa;
    private boolean czyAktywna;

}
