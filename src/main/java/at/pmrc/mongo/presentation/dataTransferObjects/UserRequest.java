package at.pmrc.mongo.presentation.dataTransferObjects;

import lombok.*;
import org.bson.types.ObjectId;

@Builder
public record UserRequest(ObjectId _id,
                          String firstname,
                          String lastname,
                          String email,
                          String password,
                          String gender,
                          String year) { }