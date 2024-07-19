package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rodzaj_uprawy")
public class CropKind implements GenericCrudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRodzajUprawy;
    private String nazwaUprawy;
    private String taryfa;
    private boolean czyAktywna;
    double wartoscRynkowa;
    double wartoscMax;
    Timestamp dataDodania;
    Timestamp dataUsuniecia;

    public String getName(){
        return nazwaUprawy;
    }
    public int getId(){
        return idRodzajUprawy;
    }

}
