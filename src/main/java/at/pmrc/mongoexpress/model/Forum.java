package at.pmrc.mongoexpress.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

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