package at.pmrc.systems.relational.mongo.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Question {
    private String content;
    private List<Vote> votes;
    private String creation_date;
}