package at.pmrc.mongo.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Document(collection = "votes")
public class votes extends AbstractPersistable<Long> {

    private int user_id;
    private int question_id;
    private boolean upvote;
}