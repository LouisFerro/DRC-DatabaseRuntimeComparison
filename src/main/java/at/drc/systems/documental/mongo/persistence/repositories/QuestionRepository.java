package at.drc.systems.documental.mongo.persistence.repositories;

import at.drc.systems.documental.mongo.model.Question;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoQuestionRepository")
public interface QuestionRepository extends MongoRepository<Question, Integer> {
}