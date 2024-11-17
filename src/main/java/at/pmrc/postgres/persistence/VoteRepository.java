package at.pmrc.postgres.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.pmrc.postgres.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
