package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "oferta")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOferta;

    @JoinColumn(name = "id_ubezpieczyciel")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private InsuranceCompany ubezpieczyciel;

    @JoinColumn(name = "id_kalkulacja")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Calculation kalkulacja;

    private String numerOferty;
    private double skladka;
    private LocalDateTime dataWygasniecia;
    private String statusOferty;

}
