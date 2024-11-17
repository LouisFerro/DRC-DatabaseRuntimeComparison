package at.pmrc.postgres.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor

@Entity(name = "votes")
public class Vote{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private int question_id;
    private boolean upvote;
}
