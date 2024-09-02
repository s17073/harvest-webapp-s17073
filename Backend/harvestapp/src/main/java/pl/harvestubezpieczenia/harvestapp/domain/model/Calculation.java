package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "kalkulacja")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idKalkulacja;

    @OneToMany(mappedBy = "kalkulacja", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApkCalculation> apk;

    @JoinColumn(name = "id_ubezpieczajacy")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    User ubezpieczajacy;
    @JoinColumn(name = "id_ubezpieczony")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    User ubezpieczony;
    @JoinColumn(name = "id_posrednik")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    User posrednik;

    @OneToMany(mappedBy = "kalkulacja", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Crop> uprawy;

    @OneToMany(mappedBy = "kalkulacja", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Livestock> zwierzeta;

    private String numerKalkulacji;
    private LocalDate dataPoczatkuOchrony;
    private LocalDate dataKoncaOchrony;
    private String statusKalkulacji;
}
