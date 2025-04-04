package at.drc.systems.documental.mongo.presentation.dataTransferObjects;

import at.drc.systems.documental.mongo.model.User;
import lombok.*;

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