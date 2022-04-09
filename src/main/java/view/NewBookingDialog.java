package view;

import entity.enums.Role;
import entity.users.RegisteredUser;
import exception.ConstraintViolation;
import util.UserValidator;

import java.util.Scanner;


public class NewBookingDialog implements EntityDialog<RegisteredUser>{
    public static Scanner sc = new Scanner(System.in);
    private static final String INVALID_FIRST_LAST_NAME_PATTERN = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";


    @Override
    public RegisteredUser input() {
        var user = new RegisteredUser();
        while (user.getFirstName() == null){
            user.setRole(Role.USER);
            System.out.println("FirstName:");

            var ans = sc.nextLine();
            if (ans.length() < 4 || ans.length() > 15) {
                System.out.println("First Name length should be between 4 and 15 characters.");
            } else if (!ans.matches(INVALID_FIRST_LAST_NAME_PATTERN)){
                System.out.println("First Name should be only with letters.");
            } else {
                user.setFirstName(ans);
            }
        }

        while (user.getLastName() == null){
            System.out.println("LastName:");
            var ans = sc.nextLine();
            if(ans.length() < 3){
                System.out.println("Error: The book title should be at least 3 characters long.");
            } else {
                user.setLastName(ans);
            }
        }
        while (user.getUsername() == null){
            System.out.println("Username:");
            var ans = sc.nextLine();
            if(ans.length() < 3){
                System.out.println("Error: The book title should be at least 3 characters long.");
            } else {
                user.setUsername(ans);
            }
        }
        while (user.getPassword() == null){
            System.out.println("Password:");
            var ans = sc.nextLine();
            if(ans.length() < 3){
                System.out.println("Error: The book title should be at least 3 characters long.");
            } else {
                user.setPassword(ans);
            }
        }
        while (user.getEmail() == null){
            System.out.println("Email:");
            var ans = sc.nextLine();
            if(ans.length() < 3){
                System.out.println("Error: The book title should be at least 3 characters long.");
            } else {
                user.setEmail(ans);
            }
        }

        return user;
    }
}
