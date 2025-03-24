package at.pmrc.systems.relational.postgres.persistence;

import at.pmrc.systems.relational.postgres.model.Question;
import at.pmrc.systems.relational.postgres.model.User;
import at.pmrc.systems.relational.postgres.model.Vote;
import at.pmrc.systems.relational.postgres.persistence.repositories.QuestionRepository;
import at.pmrc.systems.relational.postgres.persistence.repositories.UserRepository;
import at.pmrc.systems.relational.postgres.persistence.repositories.VoteRepository;

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