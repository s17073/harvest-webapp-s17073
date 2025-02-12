package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.Policy;
import pl.harvestubezpieczenia.harvestapp.domain.ports.PolicyRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.PolicyRepoJpa;

import java.util.Optional;

@Service
public class PolicyAdapterJpa implements PolicyRepo {

    private final PolicyRepoJpa policyRepoJpa;

    public PolicyAdapterJpa(PolicyRepoJpa policyRepoJpa) {
        this.policyRepoJpa = policyRepoJpa;
    }

    @Override
    public void savePolicy(Policy policy) {
        policyRepoJpa.save(policy);
    }

    @Override
    public Optional<Policy> getPolicyById(int id) {
        return policyRepoJpa.findById((long) id);
    }
}
