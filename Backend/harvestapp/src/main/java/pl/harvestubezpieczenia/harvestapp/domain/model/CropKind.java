package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

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

    @Embedded
    private InsuredValue wartoscUbezpieczenia;
    @Embedded
    private ModificationDate dataModyfikacji;

    @JsonIgnore
    public String getName(){
        return nazwaUprawy;
    }

    @JsonIgnore
    public int getId(){
        return idRodzajUprawy;
    }

}