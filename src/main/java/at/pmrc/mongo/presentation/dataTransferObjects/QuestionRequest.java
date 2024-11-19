package at.pmrc.mongo.presentation.dataTransferObjects;

import org.bson.types.ObjectId;

import java.util.Date;

public record QuestionRequest(ObjectId _id,
                              ObjectId user_id,
                              String content,
                              Date creation_date) { }