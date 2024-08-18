package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class ApkQuestionDto implements GenericDto {

    private int id;
    private String pytanie;
    private String komunikat;
    private boolean czyAktywna;

}
