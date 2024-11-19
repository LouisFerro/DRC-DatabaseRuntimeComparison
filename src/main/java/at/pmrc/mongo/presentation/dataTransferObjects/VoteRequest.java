package at.pmrc.mongo.presentation.dataTransferObjects;

import org.bson.types.ObjectId;

public record VoteRequest(ObjectId _id,
                          ObjectId user_id,
                          ObjectId question_id,
                          boolean upvote) { }