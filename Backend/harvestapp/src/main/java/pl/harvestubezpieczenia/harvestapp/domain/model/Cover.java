package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CoverName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.InsuranceClass;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Season;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ochrona")
public class Cover implements GenericCrudModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOchrona;

    @JsonUnwrapped
    @Embedded
    private CoverName nazwa;
    @JsonUnwrapped
    @Embedded
    private InsuranceClass grupaMinisterialna;

    @JsonUnwrapped
    @Embedded
    private Season taryfa;
    private String opis;
    private boolean czyUprawa;
    private boolean czyZwierze;
    private boolean czyAktywna;

    private ModificationDate dataModyfikacji;

    @JsonBackReference
    @ManyToMany(mappedBy = "ochrony",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livestock> zwierzeta = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "ochrony",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Crop> uprawy = new ArrayList<>();

    @Override
    public int getId() {
        return  idOchrona;
    }

    @Override
    public String getName() {
        return "";
    }
}
