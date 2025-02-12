package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.Policy;

import java.util.Optional;

public interface PolicyRepo {

    void savePolicy(Policy policy);
    Optional<Policy> getPolicyById(int id);

}
