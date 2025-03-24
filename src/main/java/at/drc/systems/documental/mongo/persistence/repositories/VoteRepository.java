package at.drc.systems.documental.mongo.persistence.repositories;

import at.drc.systems.documental.mongo.model.Vote;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoVoteRepository")
public interface VoteRepository extends MongoRepository<Vote, Integer> {

}