package at.pmrc.mongo.presentation.dataTransferObjects;

import at.pmrc.mongo.model.User;
import org.bson.types.ObjectId;

public record UserResult(ObjectId _id,
                         String firstname,
                         String lastname,
                         String email,
                         String password,
                         String gender,
                         String year) {

    public UserResult(User user) {
        this(user.get_id(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getGender(), user.getYear());
    }
}