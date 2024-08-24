package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropVariety;

@Data
public class CropKindVarietyDto implements GenericDto {

    private int id;
    private int idGatunek;
    private int idUprawa;
    private String taryfa;
    private String nazwaUprawy;
    private String nazwaGatunku;
    private boolean czyAktywna;

    private CropKind uprawa;
    private CropVariety gatunek;

}


