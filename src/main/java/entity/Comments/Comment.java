package entity.Comments;


import dao.Identifiable;
import entity.AbstractEntity;
import entity.users.RegisteredUser;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends AbstractEntity implements Identifiable<Long> {


    private String description;
    private LocalDateTime publishedDate;
    private int user_id;

    public Comment(String string) {
        this.description = string;
    }
}
