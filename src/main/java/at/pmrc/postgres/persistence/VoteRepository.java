package pmrc.at.postgres.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pmrc.at.postgres.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
