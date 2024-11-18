package at.pmrc.postgres.persistence;

import at.pmrc.postgres.model.*;
import at.pmrc.postgres.persistence.repositories.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component("postgresSeeder")
public class Seeder {

    private @Autowired QuestionRepository questionRepository;
    private @Autowired UserRepository userRepository;
    private @Autowired VoteRepository voteRepository;

    public void run(String... args) {
        seed("questions.json", questionRepository, Question.class, args[0]);
        seed("users.json", userRepository, User.class, args[0]);
        seed("votes.json", voteRepository, Vote.class, args[0]);
    }

    private <T> void seed(String jsonFile, CrudRepository<T, ?> repository, Class<T> entityClass, String size) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/database/migration/" + size + "/" + jsonFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: /database/migration/" + size + "/" + jsonFile);
            }

            List<T> records = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, entityClass));
            repository.saveAll(records);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to seed data for " + entityClass.getSimpleName() + " from " + jsonFile);
        }
    }
}