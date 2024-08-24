package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKind;

@Data
public class CropVarietyDto implements GenericDto {

    private String nazwaGatunku;
    private boolean czyAktywna;

    @JsonIgnore
    private CropKind uprawa;

}
