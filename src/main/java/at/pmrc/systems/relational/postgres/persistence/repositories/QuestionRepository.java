package at.pmrc.systems.relational.postgres.persistence.repositories;

import at.pmrc.systems.relational.postgres.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("postgresQuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}