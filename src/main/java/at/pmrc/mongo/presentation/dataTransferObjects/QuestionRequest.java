package at.pmrc.mongo.presentation.dataTransferObjects;

import org.bson.types.ObjectId;

import java.util.Date;

public record QuestionRequest(int _id,
                              int user_id,
                              String content,
                              Date creation_date) { }