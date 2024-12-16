package at.pmrc.mongoexpress.controllers;

import at.pmrc.mongoexpress.model.Forum;
import at.pmrc.mongoexpress.persistence.ForumRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.stream.Collectors;
import java.util.*;

@RestController
@RequestMapping("/forums")
public class ForumController {

    @Autowired
    private ForumRepository forumRepository;

    @GetMapping
    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable int id) {
        Optional<Forum> forum = forumRepository.findById(id);
        return forum.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Forum createForum(@RequestBody Forum forum) {
        return forumRepository.save(forum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forum> updateForum(@PathVariable int id, @RequestBody Forum forumDetails) {
        Optional<Forum> forumOptional = forumRepository.findById(id);

        if (forumOptional.isPresent()) {
            Forum forum = forumOptional.get();
            forum.setFirstname(forumDetails.getFirstname());
            forum.setLastname(forumDetails.getLastname());
            forum.setGender(forumDetails.getGender());
            forum.setEmail(forumDetails.getEmail());
            forum.setPassword(forumDetails.getPassword());
            forum.setYear(forumDetails.getYear());
            forum.setQuestions(forumDetails.getQuestions());

            Forum updatedForum = forumRepository.save(forum);

            return ResponseEntity.ok(updatedForum);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForum(@PathVariable int id) {
        Optional<Forum> forum = forumRepository.findById(id);

        if (forum.isPresent()) {
            forumRepository.delete(forum.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gender")
    public HttpEntity<Map<String, Long>> countByGender() {
        List<Forum> forums = forumRepository.findAll();
        Map<String, Long> genderCount = forums.stream()
                .collect(Collectors.groupingBy(Forum::getGender, Collectors.counting()));

        return genderCount.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(genderCount);
    }
}