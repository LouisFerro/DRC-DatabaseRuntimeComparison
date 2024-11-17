package at.pmrc.postgres.persistence;

import at.pmrc.postgres.model.Question;
import at.pmrc.postgres.model.User;
import at.pmrc.postgres.model.Vote;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class PostgresSeeder {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @PostConstruct
    public void seed() {
        seedQuestions();
        seedUsers();
        seedVotes();
    }

    private void seedQuestions() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/medium dump/questions.json");
        try {
            List<Question> questions = mapper.readValue(inputStream, typeReference);
            questionRepository.saveAll(questions);
            System.out.println("Questions Seeded!");
        } catch (IOException e) {
            System.out.println("Unable to seed questions: " + e.getMessage());
        }
    }

    private void seedUsers() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/medium dump/users.json");
        try {
            List<User> users = mapper.readValue(inputStream, typeReference);
            userRepository.saveAll(users);
            System.out.println("Users Seeded!");
        } catch (IOException e) {
            System.out.println("Unable to seed users: " + e.getMessage());
        }
    }

    private void seedVotes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Register JavaTimeModule
        TypeReference<List<Vote>> typeReference = new TypeReference<List<Vote>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/medium dump/votes.json");
        try {
            List<Vote> votes = mapper.readValue(inputStream, typeReference);
            voteRepository.saveAll(votes);
            System.out.println("Votes Seeded!");
        } catch (IOException e) {
            System.out.println("Unable to seed votes: " + e.getMessage());
        }
    }
}