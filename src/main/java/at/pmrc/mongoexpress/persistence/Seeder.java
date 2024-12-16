package at.pmrc.mongoexpress.persistence;

import at.pmrc.mongoexpress.model.Forum;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component("mongoExpressSeeder")
public class Seeder {

    private @Autowired ForumRepository forumRepository;

    public void run(String... args) {
        seed("forum.json", forumRepository, Forum.class, args[0]);
    }

    private <T> void seed(String jsonFile, CrudRepository<T, ?> repository, Class<T> entityClass, String size) {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getResourceAsStream("/database/migration/" + size + "/" + jsonFile)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + jsonFile);
            }

            List<T> records = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, entityClass));

            repository.saveAll(records);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to seed data for " + entityClass.getSimpleName() + " from " + jsonFile);
        }
    }
}