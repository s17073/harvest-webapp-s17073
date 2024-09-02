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

@Service
public class UserService {

    private final UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(5);
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepo userRepo, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> registerUser(User user) {
        System.out.println(user);
        user.setHaslo(encoder.encode(user.getHaslo()));
        userRepo.saveUser(user);
        return new ResponseEntity<>("User created", HttpStatus.OK);

    }

    public ResponseEntity<String> verify(User user) {

        try{
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getHaslo()));

        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is authenticated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.UNAUTHORIZED);
        }
        } catch (AuthenticationException e){
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }


    }
}
