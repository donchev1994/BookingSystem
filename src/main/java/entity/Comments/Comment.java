package entity.Comments;


import entity.AbstractEntity;
import entity.users.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    //Date, Id registeredUser

    private String description;
    private LocalDate publishedDate;
    private RegisteredUser id;

}
