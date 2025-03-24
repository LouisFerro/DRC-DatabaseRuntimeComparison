package at.pmrc.systems.documental.mongo.presentation.controllers;

import at.pmrc.systems.documental.mongo.model.User;
import at.pmrc.systems.documental.mongo.persistence.repositories.UserRepository;
import at.pmrc.systems.documental.mongo.presentation.dataTransferObjects.UserGenderResult;
import at.pmrc.systems.documental.mongo.presentation.dataTransferObjects.UserRequest;
import at.pmrc.systems.documental.mongo.presentation.dataTransferObjects.UserResult;

import lombok.*;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController("mongoUserController")
@RequestMapping("/api/mongo/user")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    private User builder(UserRequest userRequest) {
        return new User(userRequest._id(), userRequest.firstname(), userRequest.lastname(), userRequest.email(), userRequest.password(), userRequest.gender(), userRequest.year());
    }

    @GetMapping
    public HttpEntity<List<UserResult>> findAll() {
        List<UserResult> userResult = userRepository.findAll()
                                                    .stream()
                                                    .map(UserResult::new)
                                                    .toList();

        return userResult.isEmpty() ? ResponseEntity.noContent().build()
                                    : ResponseEntity.ok(userResult);
    }

    @GetMapping("/{id}")
    public HttpEntity<UserResult> findById(@PathVariable Integer id) {
        Optional<User> userResult = userRepository.findById(id);

        return userResult.map(user -> ResponseEntity.ok(new UserResult(user))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping("/gender/{id}")
    public HttpEntity<UserGenderResult> findGenderById(@PathVariable Integer id) {
        Optional<User> userResult = userRepository.findById(id);

        return userResult.map(user -> ResponseEntity.ok(new UserGenderResult(user))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userRepository.save(builder(userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Optional<User> userResult = userRepository.findById(id);

        if (userResult.isPresent()) {
            userRepository.delete(userResult.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gender")
    public HttpEntity<Map<String, Long>> countByGender() {
        List<User> users = userRepository.findAll();
        Map<String, Long> genderCount = users.stream()
                .collect(Collectors.groupingBy(User::getGender, Collectors.counting()));

        return genderCount.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(genderCount);
    }
}