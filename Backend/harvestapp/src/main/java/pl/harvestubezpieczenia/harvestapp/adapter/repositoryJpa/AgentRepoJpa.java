package pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa;

import org.springframework.stereotype.Repository;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;

@Repository
public interface AgentRepoJpa extends GenericCrudRepoJpa<Agent> {
}
