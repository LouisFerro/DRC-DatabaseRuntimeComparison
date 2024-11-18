package at.pmrc.mongoexpress.controllers;

import at.pmrc.mongoexpress.model.Forum;
import at.pmrc.mongoexpress.persistence.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Forum> getForumById(@PathVariable Integer id) {
        Optional<Forum> forum = forumRepository.findById(id);
        return forum.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Forum createForum(@RequestBody Forum forum) {
        return forumRepository.save(forum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Forum> updateForum(@PathVariable Integer id, @RequestBody Forum forumDetails) {
        Optional<Forum> forumOptional = forumRepository.findById(id);
        if (forumOptional.isPresent()) {
            Forum forum = forumOptional.get();
            forum.setFirstname(forumDetails.getFirstname());
            forum.setLastname(forumDetails.getLastname());
            forum.setGender(forumDetails.getGender());
            forum.setEnail(forumDetails.getEnail());
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
    public ResponseEntity<Void> deleteForum(@PathVariable Integer id) {
        Optional<Forum> forum = forumRepository.findById(id);
        if (forum.isPresent()) {
            forumRepository.delete(forum.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}