package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.adapters;

import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserCalcDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserPolDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.ports.UserRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.UserRepoJpa;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserCalcDto> getUserCalc(String email){
        List<Object[]> objects = userRepoJpa.getUserCalc(email);
        List<UserCalcDto> dtos = new ArrayList<>();

        for(Object[] o : objects){
            UserCalcDto dto = new UserCalcDto();

            dto.setIdKalkulacja((Integer) o[0]);
            dto.setNumerKalkulacji((String) o[1]);
            dto.setDataPoczatkuOchrony((Date) o[2]);
            dto.setDataKoncaOchrony((Date) o[3]);
            dto.setImie((String) o[4]);
            dto.setNazwisko((String) o[5]);

            dtos.add(dto);
        }

     return dtos;

    }

    @Override
    public List<UserPolDto> getUserPol(String email) {
        List<Object[]> objects = userRepoJpa.getUserPol(email);
        List<UserPolDto> dtos = new ArrayList<>();

        for(Object[] o : objects){
            UserPolDto dto = new UserPolDto();

            dto.setIdPolisa((Integer) o[0]);
            dto.setNumerPolisy((String) o[1]);
            dto.setPoczatekUbezpieczenia((Date) o[2]);
            dto.setKoniecUbezpieczenia((Date) o[3]);
            dto.setImieUbezpieczonego((String) o[4]);
            dto.setNazwiskoUbezpieczonego((String) o[5]);
            dto.setStatus((String) o[6]);

            dtos.add(dto);
        }

        return dtos;
    }
}
