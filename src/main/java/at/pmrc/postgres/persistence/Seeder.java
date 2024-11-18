package at.pmrc.postgres.persistence;

import at.pmrc.postgres.model.Question;
import at.pmrc.postgres.model.User;
import at.pmrc.postgres.model.Vote;
import at.pmrc.postgres.persistence.repositories.QuestionRepository;
import at.pmrc.postgres.persistence.repositories.UserRepository;
import at.pmrc.postgres.persistence.repositories.VoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component("postgresSeeder")
public class Seeder implements CommandLineRunner {

    private @Autowired QuestionRepository questionRepository;
    private @Autowired UserRepository userRepository;
    private @Autowired VoteRepository voteRepository;

    @Override
    public void run(String... args) {
        seed("questions.json", questionRepository, Question.class);
        seed("users.json", userRepository, User.class);
        seed("votes.json", voteRepository, Vote.class);
    }

    private <T> void seed(String jsonFile, CrudRepository<T, ?> repository, Class<T> entityClass) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/database/migration/medium/" + jsonFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + jsonFile);
            }

            List<T> records = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, entityClass));

            repository.saveAll(records);
            System.out.println("Seeded data for " + entityClass.getSimpleName() + " from " + jsonFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to seed data for " + entityClass.getSimpleName() + " from " + jsonFile);
        }
    }
}