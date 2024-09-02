package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcGetPerson;
import pl.harvestubezpieczenia.harvestapp.domain.model.Calculation;

import java.util.List;
import java.util.Map;

@Repository
public interface CalculationRepoJpa extends JpaRepository<Calculation, Long> {

    default int startNewCalculation(){
        Calculation calculation = new Calculation();
        save(calculation);
        return calculation.getIdKalkulacja();
    }

    @Query(value = "SELECT * FROM harvest.kalkulacja where id_kalkulacja = :id", nativeQuery = true)
    Calculation getCalculationById(@Param("id") int id);

    @Query(value = "select u.imie, u.nazwisko, u.pesel, u.data_urodzenia, u.email as adresEmail, t.kod_teryt as teryt, a.kod_pocztowy, a.miejscowosc, a.ulica, a.numer_domu, a.numer_mieszkania from harvest.kalkulacja k join harvest.uzytkownik u on k.id_ubezpieczajacy = u.id_uzytkownik join harvest.adres a on a.id_adres = u.id_adres join harvest.teryt t on a.id_teryt = t.id_teryt where k.id_kalkulacja = :calcid", nativeQuery = true)
    CalcGetPerson getPolicyHolder(@Param("calcid") int calcId);

    @Query(value = "select u.imie, u.nazwisko, u.pesel, u.data_urodzenia, u.email as adresEmail, t.kod_teryt as teryt, a.kod_pocztowy, a.miejscowosc, a.ulica, a.numer_domu, a.numer_mieszkania from harvest.kalkulacja k join harvest.uzytkownik u on k.id_ubezpieczony = u.id_uzytkownik join harvest.adres a on a.id_adres = u.id_adres join harvest.teryt t on a.id_teryt = t.id_teryt where k.id_kalkulacja = :calcid", nativeQuery = true)
    CalcGetPerson getPolicyInsured(@Param("calcid") int calcId);

    @Query(value = "select u.id_uprawa as id, " +
            "ru.id_rodzaj_uprawy as id_uprawy," +
            "g.id_gatunek," +
            "kg.id_klasa_gleby," +
            "ru.nazwa_uprawy as uprawa," +
            "g.nazwa_gatunku as gatunek," +
            "kg.klasa_gleby ," +
            "u.czy_nasienna ," +
            "u.powierzchnia ," +
            "u.wartosc , " +
            "u.wartosc_rynkowa, " +
            "u.powierzchnia * ru.wartosc_max as wartosc_max, " +
            "u.suma_ubezpieczenia " +
            "from harvest.kalkulacja k " +
            "join harvest.uprawa u ON u.id_kalkulacja  = k.id_kalkulacja " +
            "join harvest.rodzaj_uprawy ru on ru.id_rodzaj_uprawy  = u.id_rodzaj_uprawy " +
            "left join harvest.gatunek g on u.id_gatunek = g.id_gatunek " +
            "join harvest.klasa_gleby kg on u.id_klasa_gleby = kg.id_klasa_gleby " +
            "where k.id_kalkulacja = :calcid", nativeQuery = true)
    List<Map<String, Object>> getCrops(@Param("calcid") int calcId);

    @Query(value = "select distinct ou.id_ochrona from harvest.uprawa u left join harvest.ochrona_uprawa ou on ou.id_uprawa = u.id_uprawa where u.id_kalkulacja = :calcid and u.id_uprawa = :cropid", nativeQuery = true)
    List<Map<String, Object>> getCropCovers(@Param("calcid") int calcId,@Param("cropid") int cropId);

    @Query(value = "select t.kod_teryt as teryt, d.numer_dzialki, d.obreb, d.kod_obrebu, d.czy_poprawna, * from harvest.dzialka d inner join harvest.uprawa u on u.id_uprawa = d.id_uprawa left join harvest.teryt t on t.id_teryt = d.id_teryt " +
            "where d.id_uprawa = :cropid and u.id_kalkulacja = :calcid", nativeQuery = true)
    List<Map<String, Object>> getCropLands(@Param("calcid") int calcId,@Param("cropid") int cropId);

    @Query(value = "select u.id_uprawa as id, " +
            "ru.id_rodzaj_uprawy as id_uprawy," +
            "g.id_gatunek," +
            "kg.id_klasa_gleby," +
            "ru.nazwa_uprawy as uprawa," +
            "g.nazwa_gatunku as gatunek," +
            "kg.klasa_gleby ," +
            "u.czy_nasienna ," +
            "u.powierzchnia ," +
            "u.wartosc , " +
            "u.wartosc_rynkowa, " +
            "u.powierzchnia * ru.wartosc_max as wartosc_max, " +
            "u.suma_ubezpieczenia " +
            "from harvest.kalkulacja k " +
            "join harvest.uprawa u ON u.id_kalkulacja  = k.id_kalkulacja " +
            "join harvest.rodzaj_uprawy ru on ru.id_rodzaj_uprawy  = u.id_rodzaj_uprawy " +
            "left join harvest.gatunek g on u.id_gatunek = g.id_gatunek " +
            "join harvest.klasa_gleby kg on u.id_klasa_gleby = kg.id_klasa_gleby " +
            "where k.id_kalkulacja = :calcid and u.id_uprawa = :cropid", nativeQuery = true)
    Map<String, Object> getCrop(@Param("calcid")  int calcId,@Param("cropid") int cropid);
}
