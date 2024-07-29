package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

@Data
@Entity
@Table(name = "uprawa_gatunek")
public class CropKindVariety implements GenericCrudModel {

    @Id
    @Column(name = "id_uprawa_gatunek")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUprawaGatunek;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rodzaj_uprawy")
    private CropKind cropKind;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gatunek")
    private CropVariety cropVariety;


    @Override
    public int getId() {
        return idUprawaGatunek;
    }

    @Override
    public String getName() {
        return cropKind.getName();
    }

    @Override
    public void setDataModyfikacji(ModificationDate modificationDate) {
        cropKind.setDataModyfikacji(modificationDate);
    }

    @Override
    public ModificationDate getDataModyfikacji() {
        return cropKind.getDataModyfikacji();
    }
}
