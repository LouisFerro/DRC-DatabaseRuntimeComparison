package at.pmrc.mongo.model;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class User {

    @Id
    private int _id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String gender;
    private String year;
}