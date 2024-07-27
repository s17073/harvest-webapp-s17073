package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class CropVarietyDto implements GenericDTO{

    private String nazwaGatunku;
    private boolean czyAktywna;

}
