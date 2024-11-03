package pmrc.at.postgres.model;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Entity(name = "users")
public class User extends AbstractPersistable<Long> {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String gender;
    private String year;
}
