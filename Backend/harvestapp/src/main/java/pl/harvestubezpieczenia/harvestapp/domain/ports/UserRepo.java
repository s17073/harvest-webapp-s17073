package pl.harvestubezpieczenia.harvestapp.domain.ports;

import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserCalcDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserPolDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;

import java.util.List;

public interface UserRepo {

    void saveUser(User policyHolder);

    User getUserByUsername(String username);

    String getUserRole(String email);

    List<UserCalcDto> getUserCalc(String email);

    List<UserPolDto> getUserPol(String email);
}
