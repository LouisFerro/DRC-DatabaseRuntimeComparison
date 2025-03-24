package at.drc.systems.relational.postgres.presentation.dataTransferObjects;

import at.drc.systems.relational.postgres.model.User;

public record UserResult(int id,
                         String firstname,
                         String lastname,
                         String email,
                         String password,
                         String gender,
                         String year) {

    public UserResult(User user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getGender(), user.getYear());
    }

    public UserResult(UserRequest userRequest) {
        this(userRequest.id(), userRequest.firstname(), userRequest.firstname(), userRequest.email(), userRequest.password(), userRequest.gender(), userRequest.year());
    }
}