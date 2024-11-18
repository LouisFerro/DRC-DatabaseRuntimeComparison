package at.pmrc.mongoexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vote {
    private Boolean upvote;
    private Integer user_id;
}
