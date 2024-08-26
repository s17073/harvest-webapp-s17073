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

    private String numerKalkulacji;
    private LocalDate dataPoczatkuOchrony;
    private LocalDate dataKoncaOchrony;
    private String statusKalkulacji;
}
