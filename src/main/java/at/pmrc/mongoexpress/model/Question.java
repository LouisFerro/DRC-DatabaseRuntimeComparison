package at.pmrc.mongoexpress.model;

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
