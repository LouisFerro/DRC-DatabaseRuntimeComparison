package at.drc.systems.relational.postgres.presentation.controllers;

import at.drc.systems.relational.postgres.presentation.dataTransferObjects.UserGenderResult;
import at.drc.systems.relational.postgres.model.User;
import at.drc.systems.relational.postgres.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController("postgresUserController")
@RequestMapping("/api/postgres/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/gender/{id}")
    public HttpEntity<UserGenderResult> findGenderById(@PathVariable Integer id) {
        Optional<User> userResult = userRepository.findById(id);

        return userResult.map(user -> ResponseEntity.ok(new UserGenderResult(user))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setFirstname(userDetails.getFirstname());
            updatedUser.setLastname(userDetails.getLastname());
            updatedUser.setEmail(userDetails.getEmail());
            updatedUser.setPassword(userDetails.getPassword());
            updatedUser.setGender(userDetails.getGender());
            updatedUser.setYear(userDetails.getYear());
            return ResponseEntity.ok(userRepository.save(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.noContent().build();
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