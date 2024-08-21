package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.GenericCrudRepoJpa;

@Service
public class AgentAdapterJpa extends GenericCrudRepoAdapterJpa<Agent>{
    public AgentAdapterJpa(@Qualifier("agentRepoJpa") GenericCrudRepoJpa<Agent> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
