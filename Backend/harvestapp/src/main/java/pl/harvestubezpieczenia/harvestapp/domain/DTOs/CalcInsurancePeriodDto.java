package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class CalcInsurancePeriodDto {

    private Date dataPoczatkuOchrony;
    private Date dataKoncaOchrony;

    private List<CalcApk> apk;

}
