package at.pmrc.mongo.presentation.controllers;

import at.pmrc.mongo.model.Question;
import at.pmrc.mongo.persistence.repositories.QuestionRepository;
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
    public HttpEntity<QuestionResult> findById(@PathVariable String id) {
        Optional<Question> questionResult = questionRepository.findById(id);

        return questionResult.map(question -> ResponseEntity.ok(new QuestionResult(question))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public Question create(@RequestBody QuestionRequest questionRequest) {
        return questionRepository.save(builder(questionRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String id) {
        Optional<Question> questionResult = questionRepository.findById(new ObjectId(id));

        if (questionResult.isPresent()) {
            questionRepository.delete(questionResult.get());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}