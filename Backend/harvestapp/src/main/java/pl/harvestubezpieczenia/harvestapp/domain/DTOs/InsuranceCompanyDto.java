package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Address;

@Data
public class InsuranceCompanyDto implements GenericDto {

    private int id;
    private String nazwa;
    private String numerZakladu;
    private String numerTelefonu;
    private String numerKonta;
    private String nip;
    private String krs;
    private boolean czyAktywna;

    private int idAddress;
    private String adres;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Address addressObj;

}
