package at.pmrc.mongo.persistence.repositories;

import at.pmrc.mongo.model.Vote;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends MongoRepository<Vote, ObjectId> {

}
