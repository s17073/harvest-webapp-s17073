package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.AddressLocalization;

import java.util.List;

@Entity
@Data
@Table(name = "adres")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdres;

    @ManyToOne
    @JoinColumn(name = "id_teryt")
    private Teryt teryt;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InsuranceCompany> insuranceCompany;

    @Embedded
    private AddressLocalization lokalizacja;

    public int getId(){
        return idAdres;
    }

}
