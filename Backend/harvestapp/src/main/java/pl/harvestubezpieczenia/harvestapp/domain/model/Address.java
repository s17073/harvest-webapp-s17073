package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference(value = "teryt-adres")
    @ManyToOne
    @JoinColumn(name = "id_teryt")
    private Teryt teryt;


    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "teryt-ubezpieczyciel")
    private List<InsuranceCompany> insuranceCompany;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Agent> agents;

    @JsonManagedReference(value = "teryt-uzytkownik")
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> uzytkownik;

    @Embedded
    private AddressLocalization lokalizacja;

    public int getId(){
        return idAdres;
    }

    public String getAdress(){
        return lokalizacja.kodPocztowy() + " " +
                lokalizacja.miejscowosc() + " " +
                lokalizacja.ulica() + " " +
                lokalizacja.numerDomu() +
                (!lokalizacja.numerMieszkania().isEmpty() ? "/" + lokalizacja.numerMieszkania() : "");

    }
}
