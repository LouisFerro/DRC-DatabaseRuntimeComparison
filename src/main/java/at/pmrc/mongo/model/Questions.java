package at.pmrc.mongo.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Document(collection = "questions")
public class Questions extends AbstractPersistable<Long> {

    private int user_id;
    private String content;
    private Instant creation_date;
}