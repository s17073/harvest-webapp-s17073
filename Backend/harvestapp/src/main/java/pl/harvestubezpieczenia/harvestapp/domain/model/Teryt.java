package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "teryt")
public class Teryt implements GenericCrudModel {

    @Id
    private String idTeryt;
    private String kodTeryt;
    private String wojewodztwo;
    private String powiat;
    private String gmina;
    private String nazwa;
    private String typKod;
    private String typ;

    @JsonManagedReference(value = "teryt-adres")
    @OneToMany(mappedBy = "teryt", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    @Override
    public int getId() {
        return Integer.parseInt(idTeryt);
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
