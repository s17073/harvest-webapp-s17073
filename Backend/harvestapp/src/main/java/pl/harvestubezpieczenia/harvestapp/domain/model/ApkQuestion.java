package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.ModificationDate;
import pl.harvestubezpieczenia.harvestapp.domain.valueObjects.Question;

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
