package at.drc.systems.relational.postgres.presentation.dataTransferObjects;

public record QuestionRequest(int _id,
                              int user_id,
                              String content,
                              String creation_date) { }