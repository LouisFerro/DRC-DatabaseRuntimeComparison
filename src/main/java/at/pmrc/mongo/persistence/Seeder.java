package at.pmrc.mongo.persistence;

import at.pmrc.mongo.model.Question;
import at.pmrc.mongo.model.User;
import at.pmrc.mongo.model.Vote;
import at.pmrc.mongo.persistence.repositories.QuestionRepository;
import at.pmrc.mongo.persistence.repositories.UserRepository;
import at.pmrc.mongo.persistence.repositories.VoteRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.io.InputStream;

@Component
public class Seeder implements CommandLineRunner {

    private @Autowired QuestionRepository questionRepository;
    private @Autowired UserRepository userRepository;
    private @Autowired VoteRepository voteRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run(String... args) {
        seed("questions.json", questionRepository, Question.class);
        seed("users.json", userRepository, User.class);
        seed("votes.json", voteRepository, Vote.class);
    }

    private <T> void seed(String jsonFile, CrudRepository<T, ?> repository, Class<T> entityClass) {
        try (InputStream inputStream = getClass().getResourceAsStream("/docker/mongo/dump/" + jsonFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + jsonFile);
            }

            // Use ObjectMapper to map the JSON directly to a List of the entity type
            List<T> records = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, entityClass));

            repository.saveAll(records);
            System.out.println("Seeded data for " + entityClass.getSimpleName() + " from " + jsonFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to seed data for " + entityClass.getSimpleName() + " from " + jsonFile);
        }
    }

}