package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private CropKind uprawa;
    @JsonIgnore
    private CropVariety gatunek;

}


