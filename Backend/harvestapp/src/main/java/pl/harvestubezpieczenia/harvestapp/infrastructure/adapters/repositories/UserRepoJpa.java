package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcGetPerson;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;

import java.util.List;

@Repository
public interface UserRepoJpa extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM harvest.uzytkownik where email = :username and rola is not null", nativeQuery = true)
    User getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT rola FROM harvest.uzytkownik where email = :email and rola is not null", nativeQuery = true)
    String getUserRole(@Param("email") String email);

    @Query(value = "select k.id_kalkulacja, k.numer_kalkulacji, k.data_poczatku_ochrony, k.data_konca_ochrony, u.imie, u.nazwisko, k.status_kalkulacji from harvest.uzytkownik u join harvest.kalkulacja k on u.id_uzytkownik  = k.id_ubezpieczajacy or u.id_uzytkownik = k.id_ubezpieczony where u.email = :email order by k.id_kalkulacja DESC", nativeQuery = true)
    List<Object[]> getUserCalc(@Param("email") String email);

    @Query(value = "select p.id_polisa, p.numer_polisy, k.data_poczatku_ochrony, k.data_konca_ochrony, u2.imie, u2.nazwisko, p.status_polisy from harvest.polisa p join harvest.oferta o on o.id_oferta = p.id_oferta join harvest.kalkulacja k on k.id_kalkulacja = o.id_kalkulacja join harvest.uzytkownik u on u.id_uzytkownik = k.id_ubezpieczajacy join harvest.uzytkownik u2 on u2.id_uzytkownik = k.id_ubezpieczony where u.email = :email order by id_polisa DESC",nativeQuery = true)
    List<Object[]> getUserPol(@Param("email") String email);

    @Query(value = "select u.imie, u.nazwisko, u.pesel, u.data_urodzenia, u.email as adresEmail, t.kod_teryt as teryt, a.kod_pocztowy, a.miejscowosc, a.ulica, a.numer_domu, a.numer_mieszkania from harvest.kalkulacja k join harvest.uzytkownik u on k.id_ubezpieczajacy = u.id_uzytkownik join harvest.adres a on a.id_adres = u.id_adres join harvest.teryt t on a.id_teryt = t.id_teryt where k.id_kalkulacja = :calcid", nativeQuery = true)
    CalcGetPerson getPolicyHolder(@Param("calcid") int calcId);

    @Query(value = "SELECT u.* from harvest.uzytkownik u left join harvest.adres a on u.id_adres = a.id_adres left join harvest.teryt t on t.id_teryt = a.id_teryt where u.email = :email and u.rola is not null ", nativeQuery = true)
    User getPersonalDataFromProfile(@Param("email") String email);
}
