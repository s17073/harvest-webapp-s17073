package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dzialka")
public class Land {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDzialka;

    @JoinColumn(name = "id_teryt")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Teryt teryt;

    @JoinColumn(name = "id_uprawa")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Crop uprawa;

    private String numerDzialki;
    private String obreb;
    private String kodObrebu;
    private boolean czyPoprawna;

}
