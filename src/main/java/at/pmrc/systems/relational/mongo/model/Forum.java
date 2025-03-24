package at.pmrc.systems.relational.mongo.model;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "forums")
public class Forum {

    @Id
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private String password;
    private String year;
    private List<Question> questions;
}