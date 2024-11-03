package pmrc.at.postgres.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Entity(name = "votes")
public class Vote extends AbstractPersistable<Long> {

    private int user_id;
    private int question_id;
    private boolean upvote;
}
