package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
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

    @JsonUnwrapped
    @Embedded
    private CropKindName nazwaUprawy;
    @JsonUnwrapped
    @Embedded
    private Season taryfa;

    private boolean czyAktywna;

    @JsonUnwrapped
    @Embedded
    private InsuredValue wartoscUbezpieczenia;
    @JsonUnwrapped
    @Embedded
    private ModificationDate dataModyfikacji;

    @JsonManagedReference
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