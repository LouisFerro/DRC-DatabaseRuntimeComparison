package at.pmrc.mongo.presentation.controllers;

import at.pmrc.mongo.model.User;
import at.pmrc.mongo.persistence.repositories.UserRepository;

import at.pmrc.mongo.presentation.dataTransferObjects.UserRequest;
import at.pmrc.mongo.presentation.dataTransferObjects.UserResult;
import lombok.*;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/user")
public class UserController {

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
    public HttpEntity<UserResult> findById(@PathVariable String id) {
        Optional<User> userResult = userRepository.findById(new ObjectId(id));

        return userResult.map(user -> ResponseEntity.ok(new UserResult(user))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public User create(@RequestBody UserRequest userRequest) {
        return userRepository.save(builder(userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        Optional<User> userResult = userRepository.findById(new ObjectId(id));

        if (userResult.isPresent()) {
            userRepository.delete(userResult.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
