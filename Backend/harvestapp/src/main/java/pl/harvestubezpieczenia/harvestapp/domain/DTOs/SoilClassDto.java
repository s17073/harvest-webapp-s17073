package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class SoilClassDto implements GenericDto {

    private int id;
    private String klasaGleby;
    private String opis;
    private String taryfa;
    private boolean czyAktywna;

}
