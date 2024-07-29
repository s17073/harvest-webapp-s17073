package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.CropVarietyName;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "gatunek")
public class CropVariety implements GenericCrudModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGatunek;

    @Embedded
    private CropVarietyName nazwaGatunku;
    private boolean czyAktywna;

    @Embedded
    private ModificationDate dataModyfikacji;

    @OneToMany(mappedBy = "cropVariety", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CropKindVariety> cropKindVarieties = new HashSet<>();

    @Override
    public int getId() {
        return idGatunek;
    }

    @Override
    public String getName() {
        return nazwaGatunku.nazwaGatunku();
    }
}
