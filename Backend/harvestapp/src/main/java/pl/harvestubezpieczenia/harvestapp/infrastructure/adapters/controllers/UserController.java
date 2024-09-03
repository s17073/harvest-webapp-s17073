package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> regiserUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userService.verify(user);
    }

    @GetMapping("admin/session")
    public ResponseEntity<Boolean> getSession() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("admin/login")
    public ResponseEntity<String> adminLogin(@RequestBody User user) {
        return userService.verifyAdmin(user);
    }

}
