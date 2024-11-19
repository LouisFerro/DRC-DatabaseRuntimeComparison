package at.pmrc.mongo.presentation.dataTransferObjects;

import at.pmrc.mongo.model.Vote;

import org.bson.types.ObjectId;

public record VoteResult(ObjectId _id,
                         ObjectId user_id,
                         ObjectId question_id,
                         boolean upvote) {

    public VoteResult(Vote vote) {
        this(vote.get_id(), vote.getUser_id(), vote.getQuestion_id(), vote.isUpvote());
    }
}