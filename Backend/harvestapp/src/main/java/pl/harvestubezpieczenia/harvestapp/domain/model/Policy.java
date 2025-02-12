package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "polisa")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPolisa;

    @JoinColumn(name = "id_oferta")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Offer oferta;

    private LocalDate dataZawarciaPolisy;
    private String numerPolisy;
    private String statusPolisy;
}
