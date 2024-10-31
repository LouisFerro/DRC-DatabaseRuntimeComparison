package pmrc.at.mongo.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Document(collection = "questions")
public class questions extends AbstractPersistable<Long> {

    private int user_id;
    private String content;
    private Instant creation_date;
}