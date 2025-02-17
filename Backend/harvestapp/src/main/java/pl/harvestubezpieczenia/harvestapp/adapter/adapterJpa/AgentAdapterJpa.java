package pl.harvestubezpieczenia.harvestapp.adapter.adapterJpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.adapter.repositoryJpa.GenericCrudRepoJpa;
import pl.harvestubezpieczenia.harvestapp.domain.model.Agent;

@Service
public class AgentAdapterJpa extends GenericCrudRepoAdapterJpa<Agent>{
    public AgentAdapterJpa(@Qualifier("agentRepoJpa") GenericCrudRepoJpa<Agent> genericCrudRepoJpa) {
        super(genericCrudRepoJpa);
    }
}
