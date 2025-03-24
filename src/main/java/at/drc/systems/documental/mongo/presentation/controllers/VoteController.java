package at.drc.systems.documental.mongo.presentation.controllers;

import at.drc.systems.documental.mongo.model.Vote;
import at.drc.systems.documental.mongo.persistence.repositories.VoteRepository;
import at.drc.systems.documental.mongo.presentation.dataTransferObjects.VoteRequest;
import at.drc.systems.documental.mongo.presentation.dataTransferObjects.VoteResult;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.*;

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
    public HttpEntity<VoteResult> findById(@PathVariable Integer id) {
        Optional<Vote> voteResult = voteRepository.findById(id);

        return voteResult.map(vote -> ResponseEntity.ok(new VoteResult(vote))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public Vote create(@RequestBody VoteRequest voteRequest) {
        return voteRepository.save(builder(voteRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Integer id) {
        Optional<Vote> voteResult = voteRepository.findById(id);

        if (voteResult.isPresent()) {
            voteRepository.delete(voteResult.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}