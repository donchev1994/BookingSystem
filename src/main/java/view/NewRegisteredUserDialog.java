package view;


import dao.impl.RegisterDao;
import entity.users.RegisteredUser;


import java.util.Scanner;


public class NewRegisteredUserDialog implements EntityDialog<RegisteredUser> {
    public static Scanner sc = new Scanner(System.in);
    private static final String INVALID_FIRST_LAST_NAME_PATTERN = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
    private static final String VALIDATE_PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,13})";
    private static final String VALIDATE_EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+\\=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    RegisterDao registerDao = new RegisterDao();



    @Override
    public RegisteredUser input() {
        var user = new RegisteredUser();
        while (user.getFirstName() == null) {
            System.out.println("FirstName:");
            var ans = sc.nextLine();
            if (ans.length() < 4 || ans.length() > 15) {
                System.out.println("First Name length should be between 4 and 15 characters.");
            } else if (!ans.matches(INVALID_FIRST_LAST_NAME_PATTERN)) {
                System.out.println("First Name should be only with letters.");
            } else {
                user.setFirstName(ans);
            }
        }

        while (user.getLastName() == null) {
            System.out.println("LastName:");
            var ans = sc.nextLine();
            if (ans.length() < 3 || ans.length() > 20) {
                System.out.println("Last Name length should be between 3 and 20 characters");
            } else if (!ans.matches(INVALID_FIRST_LAST_NAME_PATTERN)) {
                System.out.println("First Name should be only with letters.");
            } else {
                user.setLastName(ans);
            }
        }
        while (user.getUsername() == null) {
            System.out.println("Username:");
            var ans = sc.nextLine();
            if (ans.length() < 5 || ans.length() > 20) {
                System.out.println("Username length should be between 5 and 20 characters");
            }else if(!registerDao.isUsernameRegistered(user.getUsername())){
                System.out.println("Username already exist.");
            } else {
                user.setUsername(ans);
            }
        }
        while (user.getPassword() == null) {
            System.out.println("Password:");
            var ans = sc.nextLine();
            if (ans.length() < 6 || ans.length() > 13) {
                System.out.println("Password length should be between 6 and 13 characters");
            }else if(!ans.matches(VALIDATE_PASSWORD_PATTERN)){
                System.out.println("Password must be contains one or more digits, one lower and one upper character and one special chars et. ( @#$%! )");
            } else {
                user.setPassword(ans);
            }
        }
        while (user.getEmail() == null) {
            System.out.println("Email:");
            var ans = sc.nextLine();
            if (ans.length() < 5 || ans.length() > 20) {
                System.out.println("Email length should be between 5 and 20 characters");
            }else if(!registerDao.isEmailRegistered(user.getEmail())) {
                System.out.println("User with email : " + user.getEmail() + " - already exist.");
            }else if(!ans.matches(VALIDATE_EMAIL_PATTERN)){
                System.out.println("Invalid email.");
            } else {
                user.setEmail(ans);
            }
        }

        return user;
    }
}

