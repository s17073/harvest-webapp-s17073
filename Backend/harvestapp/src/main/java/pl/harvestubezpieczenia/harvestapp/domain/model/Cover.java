package pl.harvestubezpieczenia.harvestapp.domain.model;

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

    @Embedded
    private CoverName nazwa;
    @Embedded
    private InsuranceClass grupaMinisterialna;

    @Embedded
    private Season taryfa;
    private String opis;
    private boolean czyUprawa;
    private boolean czyZwierze;
    private boolean czyAktywna;

    private ModificationDate dataModyfikacji;

    @ManyToMany(mappedBy = "ochrony",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livestock> zwierzeta = new ArrayList<>();

    @Override
    public int getId() {
        return  idOchrona;
    }

    @Override
    public String getName() {
        return "";
    }
}
