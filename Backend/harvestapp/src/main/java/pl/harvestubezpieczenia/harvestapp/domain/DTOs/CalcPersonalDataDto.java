package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class CalcPersonalDataDto {

    private CalcPerson ubezpieczajacy;
    private CalcPerson ubezpieczony;

}
