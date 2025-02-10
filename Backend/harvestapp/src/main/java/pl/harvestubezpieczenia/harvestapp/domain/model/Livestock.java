package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "zwierze")
public class Livestock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idZwierze;

    @JsonBackReference
    @JoinColumn(name = "id_kalkulacja")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Calculation kalkulacja;

    @JoinColumn(name = "id_rodzaj_zwierzecia")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private LivestockKind rodzajZwierzecia;
    private int liczba;
    private double wartosc;
    private double wartoscRynkowa;
    private boolean naMieso;
    private int sumaUbezpieczenia;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ochrona_zwierze",
            joinColumns = @JoinColumn(name = "id_zwierze"),
            inverseJoinColumns = @JoinColumn(name = "id_ochrona")
    )
    private List<Cover> ochrony = new ArrayList<>();

}
