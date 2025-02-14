package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;

import java.util.List;

@Repository
public interface UserRepoJpa extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM harvest.uzytkownik where email = :username and haslo is not null", nativeQuery = true)
    User getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT rola FROM harvest.uzytkownik where email = :email", nativeQuery = true)
    String getUserRole(@Param("email") String email);

    @Query(value = "select k.id_kalkulacja, k.numer_kalkulacji, k.data_poczatku_ochrony, k.data_konca_ochrony, u.imie, u.nazwisko from harvest.uzytkownik u join harvest.kalkulacja k on u.id_uzytkownik  = k.id_ubezpieczajacy or u.id_uzytkownik = k.id_ubezpieczony where u.email = :email and (status_kalkulacji <> 'POLISA' or status_kalkulacji is null)", nativeQuery = true)
    List<Object[]> getUserCalc(@Param("email") String email);

    @Query(value = "select p.id_polisa, p.numer_polisy, k.data_poczatku_ochrony, k.data_konca_ochrony, u.imie, u.nazwisko, p.status_polisy from harvest.polisa p join harvest.oferta o on o.id_oferta = p.id_oferta join harvest.kalkulacja k on k.id_kalkulacja = o.id_kalkulacja join harvest.uzytkownik u on u.id_uzytkownik = k.id_ubezpieczajacy or u.id_uzytkownik = k.id_ubezpieczony or u.id_uzytkownik = k.id_posrednik where u.email = :email",nativeQuery = true)
    List<Object[]> getUserPol(@Param("email") String email);
}
