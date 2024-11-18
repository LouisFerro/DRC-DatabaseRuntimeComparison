package at.pmrc.mongo.presentation.dataTransferObjects;

import at.pmrc.mongo.model.User;
import lombok.*;
import org.bson.types.ObjectId;

@Builder
public record UserRequest(ObjectId _id,
                          String firstname,
                          String lastname,
                          String email,
                          String password,
                          String gender,
                          String year) {

    public static UserRequest Base () {
        return new UserRequest(new ObjectId(), "", "", "", "", "", "");
    }

    public static UserRequest New (User user) {
        return new UserRequest(user.get_id(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getGender(), user.getYear());
    }
}