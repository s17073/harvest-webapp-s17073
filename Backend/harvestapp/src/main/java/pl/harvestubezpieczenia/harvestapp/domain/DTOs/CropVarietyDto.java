package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.CropKindVariety;

import java.util.List;

@Data
public class CropVarietyDto implements GenericDto {

    private String nazwaGatunku;
    private boolean czyAktywna;

    private List<CropKindVariety> lista;

}
