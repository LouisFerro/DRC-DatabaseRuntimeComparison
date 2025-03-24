package at.pmrc.systems.documental.mongo.persistence.repositories;

import at.pmrc.systems.documental.mongo.model.Vote;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoVoteRepository")
public interface VoteRepository extends MongoRepository<Vote, Integer> {

}