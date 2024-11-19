package at.pmrc.mongo.presentation.controllers;

import at.pmrc.mongo.model.Vote;
import at.pmrc.mongo.persistence.repositories.VoteRepository;
import at.pmrc.mongo.presentation.dataTransferObjects.*;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("mongoVoteController")
@RequestMapping("/api/mongo/vote")
public class VoteController {

    @Autowired
    private final VoteRepository voteRepository;

    private Vote builder(VoteRequest voteRequest) {
        return new Vote(voteRequest._id(), voteRequest.user_id(), voteRequest.question_id(), voteRequest.upvote());
    }

    @GetMapping
    public HttpEntity<List<VoteResult>> findAll() {
        List<VoteResult> voteResult = voteRepository.findAll()
                .stream()
                .map(VoteResult::new)
                .toList();

        return voteResult.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(voteResult);
    }

    @GetMapping("/{id}")
    public HttpEntity<VoteResult> findById(@PathVariable String id) {
        Optional<Vote> voteResult = voteRepository.findById(new ObjectId(id));

        return voteResult.map(vote -> ResponseEntity.ok(new VoteResult(vote))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public Vote create(@RequestBody VoteRequest voteRequest) {
        return voteRepository.save(builder(voteRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable String id) {
        Optional<Vote> voteResult = voteRepository.findById(new ObjectId(id));

        if (voteResult.isPresent()) {
            voteRepository.delete(voteResult.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}