package at.drc;

import at.drc.systems.relational.mongo.persistence.reposiories.ForumRepository;
import at.drc.systems.relational.mongo.persistence.Seeder;
import at.drc.systems.relational.postgres.model.User;
import at.drc.systems.relational.postgres.persistence.repositories.QuestionRepository;
import at.drc.systems.relational.postgres.persistence.repositories.UserRepository;
import at.drc.systems.relational.postgres.persistence.repositories.VoteRepository;
import at.drc.systems.relational.mongo.model.Forum;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;

@SpringBootTest
public class RuntimeComparisonTest {

    private @Autowired at.drc.systems.documental.mongo.persistence.repositories.QuestionRepository mongoQuestionRepository;
    private @Autowired at.drc.systems.documental.mongo.persistence.repositories.UserRepository mongoUserRepository;
    private @Autowired at.drc.systems.documental.mongo.persistence.repositories.VoteRepository mongoVoteRepository;

    private @Autowired QuestionRepository postgresQuestionRepository;
    private @Autowired UserRepository postgresUserRepository;
    private @Autowired VoteRepository postgresVoteRepository;

    private @Autowired ForumRepository forumRepository;

    private @Autowired at.drc.systems.documental.mongo.persistence.Seeder mongoSeeder;
    private @Autowired Seeder forumSeeder;
    private @Autowired at.drc.systems.relational.postgres.persistence.Seeder postgresSeeder;

    @Test
    public void runtimeComparison() throws IOException {
        System.out.println(FigletFont.convertOneLine("DRC-DatabaseRuntimeComparison"));

        System.out.format("+----------------------------------------------------+------------+------------+------------+%n");
        System.out.format("| OPERATION                                          | DATABASE   | MODEL      | TIME (ms)  |%n");
        System.out.format("+----------------------------------------------------+------------+------------+------------+%n");

        // Stage 1: Perform Mongo Tasks

        measureExecutionTime(() -> mongoSeeder.run("small"), "Seeding 10 records", "Mongo", "Relational");
        measureExecutionTime(() -> mongoSeeder.run("medium"), "Seeding 1000 records", "Mongo", "Relational");
        measureExecutionTime(() -> mongoSeeder.run("big"), "Seeding 10000 records", "Mongo", "Relational");

        measureExecutionTime(() -> mongoUserRepository.save(new at.drc.systems.documental.mongo.model.User()), "Creating 1 record ", "Mongo", "Relational");
        measureExecutionTime(() -> mongoUserRepository.findById(1), "Reading 1 record", "Mongo", "Relational");
        /* measureExecutionTime(() -> {
            User user = mongoUserRepository.findById(1).orElseThrow();
            user.setFirstname("Louis");
            mongoUserRepository.save(user);
        }, "Updating 1 record", "Mongo", "Embedded");*/
        measureExecutionTime(() -> mongoUserRepository.deleteById(1), "Deleting 1 record", "Mongo", "Relational");

        // Stage 2: Perform Mongo Express Tasks

        measureExecutionTime(() -> forumSeeder.run("small"), "Seeding 10 records", "Mongo", "Embedded");
        measureExecutionTime(() -> forumSeeder.run("medium"), "Seeding 1000 records", "Mongo", "Embedded");
        measureExecutionTime(() -> forumSeeder.run("big"), "Seeding 100000 records", "Mongo", "Embedded");

        measureExecutionTime(() -> forumRepository.save(new Forum()), "Creating 1 record", "Mongo", "Embedded");
        measureExecutionTime(() -> forumRepository.findById(1), "Reading 1 record", "Mongo", "Embedded");
        measureExecutionTime(() -> {
            Forum forum = forumRepository.findById(1).orElseThrow();
            forum.setFirstname("Louis");
            forumRepository.save(forum);
        }, "Updating 1 record", "Mongo", "Embedded");
        measureExecutionTime(() -> forumRepository.deleteById(1), "Deleting 1 record", "Mongo", "Embedded");

        // Stage 3: Perform Postgres Tasks

        measureExecutionTime(() -> postgresSeeder.run("small"), "Seeding 10 records", "Postgres", "Relational");
        measureExecutionTime(() -> postgresSeeder.run("medium"), "Seeding 1000 records", "Postgres", "Relational");
        measureExecutionTime(() -> postgresSeeder.run("big"), "Seeding 10000 records", "Postgres", "Relational");

        measureExecutionTime(() -> postgresUserRepository.save(new User()), "Creating 1 record", "Postgres", "Relational");
        measureExecutionTime(() -> postgresUserRepository.findById(1), "Creating 1 record", "Postgres", "Relational");
        measureExecutionTime(() -> {
            User user = postgresUserRepository.findById(1).get();
            user.setFirstname("Louis");
            postgresUserRepository.save(user);
        }, "Updating 1 record", "Postgres", "Relational");
        measureExecutionTime(() -> postgresUserRepository.deleteById(1), "Deleting 1 record", "Postgres", "Relational");
    }

    private void measureExecutionTime(Runnable operation, String taskDescription, String database, String type) {
        long startTime = System.currentTimeMillis();
        operation.run();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.format("| %-50s | %-10s | %-10s | %-10d |%n", taskDescription, database, type, executionTime);
        System.out.format("+----------------------------------------------------+------------+------------+------------+%n");
    }
}