package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;

@Data
public class InsuranceCompanyDto implements GenericDTO{

    private String nazwa;
    private String numerZakladu;
    private String numerTelefonu;
    private String numerKonta;
    private String nip;
    private String krs;
    private boolean czyAktywna;

}
