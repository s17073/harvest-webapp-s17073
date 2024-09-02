package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.model.User;

public interface UserRepo {

    void saveUser(User policyHolder);

    User getUserByUsername(String username);
}
