package at.pmrc.postgres.presentation.dataTransferObjects;

import at.pmrc.mongo.model.User;
import lombok.Builder;

@Builder
public record UserRequest(int _id,
                          String firstname,
                          String lastname,
                          String email,
                          String password,
                          String gender,
                          String year) {

    public static UserRequest New (User user) {
        return new UserRequest(user.get_id(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getGender(), user.getYear());
    }
}