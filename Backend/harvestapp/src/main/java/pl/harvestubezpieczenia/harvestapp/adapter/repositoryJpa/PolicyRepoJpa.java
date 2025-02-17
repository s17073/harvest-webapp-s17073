package pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Policy;

@Repository
public interface PolicyRepoJpa extends JpaRepository<Policy, Long> {
}
