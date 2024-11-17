package at.pmrc.postgres.persistence;


import at.pmrc.postgres.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
