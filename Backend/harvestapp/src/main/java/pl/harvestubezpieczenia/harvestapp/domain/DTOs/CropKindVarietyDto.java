package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class CropKindVarietyDto implements GenericDTO{

    private int id;
    private String uprawa;
    private String gatunek;

}


