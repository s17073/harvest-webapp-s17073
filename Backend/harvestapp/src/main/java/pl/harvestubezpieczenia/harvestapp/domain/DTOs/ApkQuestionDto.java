package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class ApkQuestionDto implements GenericDTO {

    private String pytanie;
    private String komunikat;
    private boolean czyAktywna;

}
