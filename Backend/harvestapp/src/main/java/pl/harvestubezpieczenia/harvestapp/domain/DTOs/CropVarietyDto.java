package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CropVarietyDto implements GenericDto {

    private String nazwaGatunku;
    private boolean czyAktywna;

    private List<String> uprawy = new ArrayList<>();

}
