package at.drc.systems.documental.mongo.presentation.controllers;

import at.drc.systems.documental.mongo.model.Question;
import at.drc.systems.documental.mongo.persistence.repositories.QuestionRepository;
import at.drc.systems.documental.mongo.presentation.dataTransferObjects.QuestionRequest;
import at.drc.systems.documental.mongo.presentation.dataTransferObjects.QuestionResult;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import java.util.*;

@RequiredArgsConstructor
@RestController("mongoQuestionController")
@RequestMapping("/api/mongo/question")
public class QuestionController {

    @Autowired
    private final QuestionRepository questionRepository;

    private Question builder(QuestionRequest questionRequest) {
        return new Question(questionRequest._id(), questionRequest.user_id(), questionRequest.content(), questionRequest.creation_date());
    }

    @GetMapping
    public HttpEntity<List<QuestionResult>> findAll() {
        List<QuestionResult> questionResult = questionRepository.findAll()
                .stream()
                .map(QuestionResult::new)
                .toList();

        return questionResult.isEmpty() ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(questionResult);
    }

    @GetMapping("/{id}")
    public HttpEntity<QuestionResult> findById(@PathVariable Integer id) {
        Optional<Question> questionResult = questionRepository.findById(id);

        return questionResult.map(question -> ResponseEntity.ok(new QuestionResult(question))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public Question create(@RequestBody QuestionRequest questionRequest) {
        return questionRepository.save(builder(questionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        Optional<Question> questionResult = questionRepository.findById(id);

        if (questionResult.isPresent()) {
            questionRepository.delete(questionResult.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}