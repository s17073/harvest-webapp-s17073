package pl.harvestubezpieczenia.harvestapp.infrastructure.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.CalcPerson;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserCalcDto;
import pl.harvestubezpieczenia.harvestapp.domain.DTOs.UserPolDto;
import pl.harvestubezpieczenia.harvestapp.domain.model.User;
import pl.harvestubezpieczenia.harvestapp.domain.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> regiserUser(@RequestBody CalcPerson calcPerson) {
        return userService.registerUser(calcPerson);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        return userService.verify(user);
    }


    @GetMapping("admin/session")
    public ResponseEntity<Boolean> getSession() {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("admin/login")
    public ResponseEntity<Map<String, String>> adminLogin(@RequestBody User user) {
        return userService.verifyAdmin(user);
    }

    @GetMapping("{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @GetMapping("{email}/calc")
    public ResponseEntity<List<UserCalcDto>> getUserCalc(@PathVariable String email) {
        return userService.getUserCalc(email);
    }

    @GetMapping("{email}/pol")
    public ResponseEntity<List<UserPolDto>> getUserPol(@PathVariable String email) {
        return userService.getUserPol(email);
    }


}
