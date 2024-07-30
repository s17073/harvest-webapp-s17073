package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;

@Data
public class InsuranceCompanyDto implements GenericDto {

    private String nazwa;
    private String numerZakladu;
    private String numerTelefonu;
    private String numerKonta;
    private String nip;
    private String krs;
    private boolean czyAktywna;

    private Address address;

}
