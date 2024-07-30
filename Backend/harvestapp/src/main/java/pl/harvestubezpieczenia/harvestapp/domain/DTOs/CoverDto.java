package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class CoverDto implements GenericDto {

    private String nazwa;
    private String grupaMinisterialna;
    private String taryfa;
    private String opis;
    private boolean czyUprawa;
    private boolean czyZwierze;
    private boolean czyAktywna;

}
