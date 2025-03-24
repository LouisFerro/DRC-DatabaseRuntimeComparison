package at.drc.systems.relational.mongo.persistence.reposiories;

import at.drc.systems.relational.mongo.model.Forum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoExpressForumRepository")
public interface ForumRepository extends MongoRepository<Forum, Integer> {

}