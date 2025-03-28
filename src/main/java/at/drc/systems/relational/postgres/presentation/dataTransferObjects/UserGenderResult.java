package at.drc.systems.relational.postgres.presentation.dataTransferObjects;

import at.drc.systems.relational.postgres.model.User;

public record UserGenderResult(int id,
                               String firstname,
                               String lastname,
                               String gender) {

    public UserGenderResult(User user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getGender());
    }

    public UserGenderResult(UserRequest userRequest) {
        this(userRequest.id(), userRequest.firstname(), userRequest.firstname(), userRequest.gender());
    }
}