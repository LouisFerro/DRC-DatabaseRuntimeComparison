package pmrc.at.postgres.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmrc.at.postgres.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
