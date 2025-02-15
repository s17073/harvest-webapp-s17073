package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcPerson;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserCalcDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserPolDto;
import pl.harvestubezpieczenia.harvestapp.domain.mappers.CalcPersonMapper;
import pl.harvestubezpieczenia.harvestapp.domain.model.Teryt;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.ports.UserRepo;
import pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.repositories.TerytRepoJpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JWTService jwtService;
    private final CalcPersonMapper calcPersonMapper;
    private final TerytRepoJpa terytRepoJpa;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepo userRepo, JWTService jwtService, AuthenticationManager authenticationManager, CalcPersonMapper calcPersonMapper, TerytRepoJpa terytRepoJpa) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.calcPersonMapper = calcPersonMapper;
        this.terytRepoJpa = terytRepoJpa;
    }

    public ResponseEntity<String> registerUser(CalcPerson calcPerson) {

        Teryt teryt = terytRepoJpa.getTeryt(calcPerson.getTeryt());
        User user = calcPersonMapper.mapToEntity(calcPerson, teryt);
        user.setHaslo(calcPerson.getHaslo());
        user.setRola(calcPerson.getRola());
        user.setHaslo(encoder.encode(user.getHaslo()));

        userRepo.saveUser(user);


        return new ResponseEntity<>("User created", HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> verifyAgent(User user) {
        Map<String, String> response = new HashMap<>();

        if (Objects.equals(getUserRole(user.getEmail()), "AGENT")){
            return verify(user);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Map<String, String>> verifyAdmin(User user) {
        Map<String, String> response = new HashMap<>();

        if (Objects.equals(getUserRole(user.getEmail()), "ADMIN")){
            return verify(user);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Map<String, String>> verify(User user) {

        Map<String, String> response = new HashMap<>();

        try{
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHaslo()));

        if (authentication.isAuthenticated()) {
            response.put("role", getUserRole(user.getEmail()));
            response.put("token", jwtService.generateToken(user.getEmail()));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        } catch (AuthenticationException e){
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }

    public String getUserRole(String email) {
        return userRepo.getUserRole(email);
    }

    public ResponseEntity<User> getUser(String email) {
        return new ResponseEntity<>(userRepo.getUserByUsername(email), HttpStatus.OK);
    }

    public ResponseEntity<List<UserCalcDto>> getUserCalc(String email) {

        return new ResponseEntity<>(userRepo.getUserCalc(email), HttpStatus.OK);

    }

    public ResponseEntity<List<UserPolDto>> getUserPol(String email) {

        return new ResponseEntity<>(userRepo.getUserPol(email), HttpStatus.OK);

    }
}
