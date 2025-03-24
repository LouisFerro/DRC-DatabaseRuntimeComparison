package at.pmrc.systems.relational.postgres.presentation.controllers;

import at.pmrc.systems.relational.postgres.model.Vote;
import at.pmrc.systems.relational.postgres.persistence.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("postgresVoteController")
@RequestMapping("/api/postgres/vote")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVoteById(@PathVariable int id) {
        Optional<Vote> vote = voteRepository.findById(id);
        return vote.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return voteRepository.save(vote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable int id, @RequestBody Vote voteDetails) {
        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.isPresent()) {
            Vote updatedVote = vote.get();
            updatedVote.setUser_id(voteDetails.getUser_id());
            updatedVote.setQuestion_id(voteDetails.getQuestion_id());
            updatedVote.setUpvote(voteDetails.isUpvote());
            return ResponseEntity.ok(voteRepository.save(updatedVote));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable int id) {
        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.isPresent()) {
            voteRepository.delete(vote.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}