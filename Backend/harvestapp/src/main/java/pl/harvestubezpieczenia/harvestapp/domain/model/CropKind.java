package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropKindName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "rodzaj_uprawy")
public class CropKind implements GenericCrudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRodzajUprawy;

    @Embedded
    private CropKindName nazwaUprawy;
    @Embedded
    private Season taryfa;

    private boolean czyAktywna;

    @Embedded
    private InsuredValue wartoscUbezpieczenia;
    @Embedded
    private ModificationDate dataModyfikacji;

    @OneToMany(mappedBy = "cropKind", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CropKindVariety> cropKindVarieties = new ArrayList<>();

    @JsonIgnore
    public String getName(){
        return nazwaUprawy.nazwaUprawy();
    }

    @JsonIgnore
    public int getId(){
        return idRodzajUprawy;
    }

}