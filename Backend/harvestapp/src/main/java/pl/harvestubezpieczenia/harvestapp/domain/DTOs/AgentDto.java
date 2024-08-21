package pl.harvestubezpieczenia.harvestapp.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;

@Data
public class AgentDto implements GenericDto{

    private int id;
    private String nazwa;
    private String kodAgencji;
    private String nip;
    private String krs;
    private String nrTel;
    private boolean czyAktywna;
    private short liczbaPosrednikow;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String teryt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int idAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String adres;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AddressDto addressData;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Teryt terytData;
}
