package pl.harvestubezpieczenia.harvestapp.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "uzytkownik")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUzytkownik;

    @JoinColumn(name = "id_adres")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    int idKonta;
    String imie;
    String nazwisko;
    String pesel;
    LocalDate dataUrodzenia;
    short wiek;
    String email;
    String haslo;
    String kodPosrednika;
    String numerTelefonu;

}
