package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.ports.UserRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.UserRepoJpa;

@Service
public class UserAdapterJpa implements UserRepo {
    private final UserRepoJpa userRepoJpa;

    public UserAdapterJpa(UserRepoJpa userRepoJpa) {
        this.userRepoJpa = userRepoJpa;
    }

    @Override
    public void saveUser(User policyHolder) {
        userRepoJpa.save(policyHolder);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepoJpa.getUserByUsername(username);
    }

    @Override
    public String getUserRole(String email) {
        return userRepoJpa.getUserRole(email);
    }
}
