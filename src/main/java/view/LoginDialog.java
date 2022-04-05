package view;

import entity.users.RegisteredUser;
import entity.users.User;

import java.util.Scanner;

public class LoginDialog implements EntityDialog<RegisteredUser> {
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public RegisteredUser input() {
        System.out.println("Username: ");
        System.out.println("Password: ");
        return null;
    }
}
