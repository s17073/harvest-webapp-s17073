package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "uprawa")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUprawa;

    @JoinColumn(name = "id_kalkulacja")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Calculation kalkulacja;

    @JoinColumn(name = "id_klasa_gleby")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SoilClass klasaGleby;

    @JoinColumn(name = "id_rodzaj_uprawy")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CropKind rodzajUprawy;

    @JoinColumn(name = "id_gatunek")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CropVariety gatunek;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ochrona_uprawa",
            joinColumns = @JoinColumn(name = "id_uprawa"),
            inverseJoinColumns = @JoinColumn(name = "id_ochrona")
    )
    private List<Cover> ochrony = new ArrayList<>();

    @OneToMany(mappedBy = "uprawa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Land> dzialki = new ArrayList<>();

    private boolean czyNasienna;
    private double powierzchnia;
    private double wartosc;
    private double wartoscRynkowa;
    private int sumaUbezpieczenia;

}
