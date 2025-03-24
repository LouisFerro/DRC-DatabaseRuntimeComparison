package at.drc.systems.relational.postgres.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.drc.systems.relational.postgres.model.User;

@Repository("postgresUserRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
}