package at.pmrc.postgres.presentation.controllers;

import at.pmrc.postgres.model.Question;
import at.pmrc.postgres.persistence.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("postgresQuestionController")
@RequestMapping("/api/postgres/question")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        Optional<Question> question = questionRepository.findById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @RequestBody Question questionDetails) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            Question updatedQuestion = question.get();
            updatedQuestion.setContent(questionDetails.getContent());
            updatedQuestion.setUser_id(questionDetails.getUser_id());
            return ResponseEntity.ok(questionRepository.save(updatedQuestion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}