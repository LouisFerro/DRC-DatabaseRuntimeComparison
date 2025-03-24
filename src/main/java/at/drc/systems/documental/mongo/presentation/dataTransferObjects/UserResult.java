package at.drc.systems.documental.mongo.presentation.dataTransferObjects;

import at.drc.systems.documental.mongo.model.User;

public record UserResult(int _id,
                         String firstname,
                         String lastname,
                         String email,
                         String password,
                         String gender,
                         String year) {

    public UserResult(User user) {
        this(user.get_id(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword(), user.getGender(), user.getYear());
    }

    public UserResult(UserRequest userRequest) {
        this(userRequest._id(), userRequest.firstname(), userRequest.firstname(), userRequest.email(), userRequest.password(), userRequest.gender(), userRequest.year());
    }
}