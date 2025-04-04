package at.drc.systems.relational.postgres.presentation.dataTransferObjects;

import at.drc.systems.relational.postgres.model.User;
import lombok.Builder;

@Builder
public record UserRequest(int id,
                          String firstname,
                          String lastname,
                          String email,
                          String password,
                          String gender,
                          String year) {

    public static UserRequest New (User user) {
        return new UserRequest(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getGender(), user.getYear());
    }
}