package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.SoilClassName;

@Data
@Entity
@Table(name = "klasa_gleby")
public class SoilClass implements GenericCrudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idKlasaGleby;

    @Enumerated
    private SoilClassName klasaGleby;
    private String opis;
    private boolean czyAktywna;

    @Embedded
    private Season taryfa;
    @Embedded
    private ModificationDate dataModyfikacji;


    @JsonIgnore
    public int getId() {
        return idKlasaGleby;
    }

    @JsonIgnore
    public String getName() {
        return "";
    }
}
