package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;

@Repository
public interface UserRepoJpa extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM harvest.uzytkownik where email = :username and haslo is not null", nativeQuery = true)
    User getUserByUsername(@Param("username") String username);
}
