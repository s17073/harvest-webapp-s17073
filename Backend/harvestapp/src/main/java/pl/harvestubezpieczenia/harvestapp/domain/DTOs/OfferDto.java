package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;
import pl.harvestubezpieczenia.harvestapp.domain.model.InsuranceCompany;

import java.time.LocalDateTime;

@Data
public class OfferDto {

    private Long idOferta;
    private InsuranceCompany ubezpieczyciel;
    private Calculation kalkulacja;
    private String numerOferty;
    private double skladka;
    private LocalDateTime dataWygasniecia;
    private String statusOferty;
    private double sumaUbezpieczeniaUpraw;
    private double sumaUbezpieczeniaZwierzat;

}
