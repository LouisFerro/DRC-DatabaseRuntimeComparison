package at.pmrc.mongo.model;

import lombok.*;
import jakarta.persistence.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Document(collection = "votes")
public class Vote {

    @Id
    private int _id;
    private int user_id;
    private int question_id;
    private boolean upvote;
}