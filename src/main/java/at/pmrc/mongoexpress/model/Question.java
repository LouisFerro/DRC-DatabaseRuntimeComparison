package at.pmrc.mongoexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Question {
    private String content;
    private List<Vote> votes;
    private String creation_date;
}
