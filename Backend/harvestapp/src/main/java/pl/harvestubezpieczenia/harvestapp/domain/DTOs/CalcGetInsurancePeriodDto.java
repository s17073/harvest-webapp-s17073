package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CalcGetInsurancePeriodDto {

    private LocalDate dataPoczatkuOchrony;
    private LocalDate dataKoncaOchrony;

    List<CalcGetApkDto> apk;
}
