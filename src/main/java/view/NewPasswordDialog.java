package view;

import dao.impl.SettingsDao;
import entity.users.RegisteredUser;
import entity.users.User;

import java.sql.SQLException;

import static view.NewRegisteredUserDialog.sc;

public class NewPasswordDialog implements EntityDialog<User> {

    private static final String VALIDATE_PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,13})";
    SettingsDao settingsDao = new SettingsDao();

    @Override
    public User input() {
        System.out.println("Enter username:");
        var username = sc.nextLine();
        var user = settingsDao.getUserSettings(username);
        var newPassword = "";
        while (!user.getPassword().equals(newPassword)) {
            System.out.println("Enter new password");
            var password = sc.nextLine();
            if (password.length() < 6 || password.length() > 13) {
                System.out.println("Password length should be between 6 and 13 characters");
            } else if (!password.matches(VALIDATE_PASSWORD_PATTERN)) {
                System.out.println("Password must be contains one or more digits, one lower and one upper character and one special chars et. ( @#$%! )");
            } else {
                newPassword = password;
                user.setPassword(password);
            }
        }

        return user;
    }


}
