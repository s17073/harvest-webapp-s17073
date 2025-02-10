package pl.harvestubezpieczenia.harvestapp.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Question;

import java.util.List;

@Data
@Entity
@Table(name = "apk")
public class ApkQuestion implements GenericCrudModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idApk;

    @Embedded
    private Question pytanie;
    private String komunikat;
    private boolean czyAktywna;

    @JsonManagedReference
    @OneToMany(mappedBy = "apk", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApkCalculation> calculation;

    @Embedded
    private ModificationDate dataModyfikacji;

    @Override
    public int getId() {
        return idApk;
    }

    @Override
    public String getName() {
        return "question: " + pytanie.pytanie();
    }
}
