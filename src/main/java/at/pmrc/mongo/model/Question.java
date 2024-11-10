package at.pmrc.mongo.model;

import lombok.*;
import java.util.Date;

import jakarta.persistence.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

@Document(collection = "questions")
public class Question {

    @Id
    private ObjectId _id;
    private int user_id;
    private String content;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date creation_date;
}