package at.pmrc.mongoexpress.persistence;

import at.pmrc.mongoexpress.model.Forum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoExpressForumRepository")
public interface ForumRepository extends MongoRepository<Forum, int> {
    // Additional query methods can be added if needed
}