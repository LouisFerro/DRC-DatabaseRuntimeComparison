package at.pmrc.mongo.presentation.dataTransferObjects;

public record VoteRequest(Integer _id,
                          Integer user_id,
                          Integer question_id,
                          boolean upvote) { }