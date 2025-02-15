package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.*;

@Data
@Entity
@Table(name = "ubezpieczyciel")
public class InsuranceCompany implements GenericCrudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUbezpieczyciel;
    @JsonUnwrapped
    @Embedded
    private InsuranceCompanyName nazwa;
    @JsonUnwrapped
    @Embedded
    private InsuranceCompanyNumber numerZakladu;
    @JsonUnwrapped
    @Embedded
    private PhoneNumber numerTelefonu;
    @JsonUnwrapped
    @Embedded
    private BankAccountNumber numerKonta;
    @JsonUnwrapped
    @Embedded
    private Nip nip;
    @JsonUnwrapped
    @Embedded
    private Krs krs;

    @ManyToOne
    @JoinColumn(name = "id_adres")
    @JsonBackReference(value = "teryt-ubezpieczyciel")
    private Address address;

    private boolean czyAktywna;
    @JsonUnwrapped
    private ModificationDate dataModyfikacji;

    @Override
    public int getId() {
        return idUbezpieczyciel;
    }

    @Override
    public String getName() {
        return nazwa.nazwa();
    }

}
