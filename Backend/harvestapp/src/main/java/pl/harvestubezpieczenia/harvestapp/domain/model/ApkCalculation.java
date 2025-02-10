package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "apk_kalkulacja")
public class ApkCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idApkKalkulacja;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_apk")
    private ApkQuestion apk;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_kalkulacja")
    private Calculation kalkulacja;

    private boolean odpowiedz;
}
