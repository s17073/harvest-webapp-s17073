package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.util.Set;

@Data
@Entity
@Table(name = "teryt")
public class Teryt implements GenericCrudModel {

    @Id
    private int idTeryt;
    private String kodTeryt;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    private String typ;

    @OneToMany(mappedBy = "teryt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> addresses;

    @Override
    public int getId() {
        return idTeryt;
    }

    @Override
    public String getName() {
        return kodTeryt;
    }

    @Override
    public void setDataModyfikacji(ModificationDate modificationDate) {
    }

    @Override
    public ModificationDate getDataModyfikacji() {
        return null;
    }
}
