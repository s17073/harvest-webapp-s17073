package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rodzaj_uprawy")
public class CropKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rodzaj_uprawy;
    private String nazwa_uprawy;
    private String taryfa;
    private boolean czy_aktywna;
    double wartosc_rynkowa;
    double wartosc_max;
    Timestamp data_dodania;
    Timestamp data_usuniecia;

}
