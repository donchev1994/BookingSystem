package view;

import entity.Comments.Comment;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class NewCommentDialog implements EntityDialog<Comment> {
    public static Scanner scanner = new Scanner(System.in);


    @Override
    public Comment input() throws SQLException {
        var comment = new Comment();
        while (comment.getDescription() == null){
            System.out.println("Set description:");
            var answer = scanner.nextLine();
            if (answer.length() < 20 || answer.length() > 250) {
                System.out.println("Comment description length should be between 20 and 250 characters.");
            } else {
                comment.setDescription(answer);
            }
        }

        comment.setPublishedDate(LocalDateTime.now());



        return comment;
    }
}
