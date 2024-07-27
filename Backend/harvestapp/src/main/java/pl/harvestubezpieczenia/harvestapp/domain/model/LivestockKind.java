package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuredValue;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.LivestockKindName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

@Data
@Entity
@Table(name = "rodzaj_zwierzecia")
public class LivestockKind implements GenericCrudModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRodzajZwierzecia;

    @Embedded
    private LivestockKindName nazwa;

    @Embedded
    private Season taryfa;
    private boolean czyAktywna;
    @Embedded
    private InsuredValue wartoscUbezpieczenia;
    private ModificationDate dataModyfikacji;

    @JsonIgnore
    public String getName(){
        return nazwa.nazwa();
    }

    @JsonIgnore
    public int getId(){
        return idRodzajZwierzecia;
    }

}
