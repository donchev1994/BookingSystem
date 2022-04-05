package view;

import entity.users.RegisteredUser;
import entity.users.User;

import java.util.Scanner;

public class LoginDialog implements EntityDialog<RegisteredUser> {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public RegisteredUser input() {
        var user = new User();
        while(user.getEmail() == null){
            System.out.println("Username: ");
            var answer = scanner.nextLine();
            user.setEmail(answer);

        }

        while (user.getPassword() == null){
            System.out.println("Password: ");
            var answer = scanner.nextLine();
            if(user.getPassword().contains(answer)){

            }

        }

        return null;
    }
}
