package at.pmrc;

import at.pmrc.foundation.*;
import at.pmrc.mongo.model.*;

import at.pmrc.mongoexpress.model.Forum;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class RuntimeComparisonTest {

    private @Autowired at.pmrc.mongo.persistence.repositories.QuestionRepository mongoQuestionRepository;
    private @Autowired at.pmrc.mongo.persistence.repositories.UserRepository mongoUserRepository;
    private @Autowired at.pmrc.mongo.persistence.repositories.VoteRepository mongoVoteRepository;

    private @Autowired at.pmrc.postgres.persistence.repositories.QuestionRepository postgresQuestionRepository;
    private @Autowired at.pmrc.postgres.persistence.repositories.UserRepository postgresUserRepository;
    private @Autowired at.pmrc.postgres.persistence.repositories.VoteRepository postgresVoteRepository;

    private @Autowired at.pmrc.mongoexpress.persistence.ForumRepository forumRepository;

    private @Autowired at.pmrc.mongo.persistence.Seeder mongoSeeder;
    private @Autowired at.pmrc.mongoexpress.persistence.Seeder forumSeeder;
    private @Autowired at.pmrc.postgres.persistence.Seeder postgresSeeder;

    @Test
    public void performMongoTasks() {
        measureExecutionTime(() -> mongoSeeder.run("small"), "Seeding Mongo Database with 10 records");
        measureExecutionTime(() -> mongoSeeder.run("medium"), "Seeding Mongo Database with 1000 records");
        measureExecutionTime(() -> mongoSeeder.run("big"), "Seeding Mongo Database with 10000 records");

        measureExecutionTime(() -> mongoUserRepository.save(new at.pmrc.mongo.model.User()), "MongoDB Create Operation");
        measureExecutionTime(() -> mongoUserRepository.findById(1), "MongoDB Read Operation");
        measureExecutionTime(() -> {
            User user = mongoUserRepository.findById(1).orElseThrow();
            user.setFirstname("Louis");
            mongoUserRepository.save(user);
        }, "MongoDB Update Operation");
        measureExecutionTime(() -> mongoUserRepository.deleteById(1), "MongoDB Delete Operation");
    }

    @Test
    public void performMongoExpressTasks() {
        measureExecutionTime(() -> forumSeeder.run("small"), "Seeding Mongo Express Database with 10 records");
        measureExecutionTime(() -> forumSeeder.run("medium"), "Seeding Mongo Express Database with 10 records");
        measureExecutionTime(() -> forumSeeder.run("big"), "Seeding Mongo Express Database with 10 records");

        measureExecutionTime(() -> forumRepository.save(new at.pmrc.mongoexpress.model.Forum()), "MongoDB Create Operation");
        measureExecutionTime(() -> forumRepository.findById(1), "MongoDB Read Operation");
        measureExecutionTime(() -> {
            Forum forum = forumRepository.findById(1).orElseThrow();
            forum.setFirstname("Louis");
            forumRepository.save(forum);
        }, "MongoDB Update Operation");
        measureExecutionTime(() -> forumRepository.deleteById(1), "MongoDB Delete Operation");
    }

    @Test
    public void performPostgresTasks() {
        measureExecutionTime(() -> postgresSeeder.run("small"), "Seeding Postgres Database with 10 records");
        measureExecutionTime(() -> postgresSeeder.run("medium"), "Seeding Postgres Database with 1000 records");
        measureExecutionTime(() -> postgresSeeder.run("big"), "Seeding Postgres Database with 10000 records");

        measureExecutionTime(() -> postgresUserRepository.save(new at.pmrc.postgres.model.User()), "MongoDB Create Operation");
        measureExecutionTime(() -> postgresUserRepository.findById(1), "MongoDB Read Operation");
        measureExecutionTime(() -> {
            at.pmrc.postgres.model.User user = postgresUserRepository.findById(1).orElseThrow();
            user.setFirstname("Louis");
            postgresUserRepository.save(user);
        }, "MongoDB Update Operation");
        measureExecutionTime(() -> postgresUserRepository.deleteById(1), "MongoDB Delete Operation");
    }

    private void measureExecutionTime(Runnable task, String taskDescription) {
        long startTime = System.currentTimeMillis();
        task.run();
        long endTime = System.currentTimeMillis();

        System.out.println(taskDescription + ": " + (endTime - startTime) + "ms");
    }
}
