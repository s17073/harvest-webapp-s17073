package pl.harvestubezpieczenia.harvestapp.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.ports.UserRepo;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepo userRepo, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> registerUser(User user) {
        user.setRola("AGENT");
        user.setHaslo(encoder.encode(user.getHaslo()));
        userRepo.saveUser(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }

    public ResponseEntity<String> verifyAgent(User user) {
        if (Objects.equals(getUserRole(user.getEmail()), "AGENT")){
            return verify(user);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> verifyAdmin(User user) {

        if (Objects.equals(getUserRole(user.getEmail()), "ADMIN")){
            return verify(user);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> verify(User user) {

        try{
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHaslo()));

        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(user.getEmail()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.UNAUTHORIZED);
        }
        } catch (AuthenticationException e){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

    }

    public String getUserRole(String email) {
        return userRepo.getUserRole(email);
    }

}
