package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;

@Repository
public interface AgentRepoJpa extends GenericCrudRepoJpa<Agent> {
}
