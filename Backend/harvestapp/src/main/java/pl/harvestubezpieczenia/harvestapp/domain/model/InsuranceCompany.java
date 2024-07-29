package pl.harvestubezpieczenia.harvestapp.domain.model;

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
    @Embedded
    private InsuranceCompanyName nazwa;
    @Embedded
    private InsuranceCompanyNumber numerZakladu;
    @Embedded
    private PhoneNumber numerTelefonu;
    @Embedded
    private BankAccountNumber numerKonta;
    @Embedded
    private Nip nip;
    @Embedded
    private Krs krs;

    @ManyToOne
    @JoinColumn(name = "id_adres")
    private Address address;

    private boolean czyAktywna;
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
