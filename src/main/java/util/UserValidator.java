package util;

import entity.users.User;
import exception.ConstraintViolation;
import exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    private static final String INVALID_FIRST_LAST_NAME_PATTERN = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";
    private static final String INVALID_USERNAME_PATTERN = "\\W+";
    private static final String EMAIL_VALIDATION_PATTERN = "/^[a-zA-Z0-9_!#$%&'*+\\=?`{|}~^.-]+@[a-zA-Z0-9.-]+$/gm";

    public void validate(User user) throws ConstraintViolationException {
        List<ConstraintViolation> violations = new ArrayList<>();

        if (user.getFirstName().length() < 2 || user.getFirstName().length() > 15) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " first name", user.getFirstName(),
                            "First Name length should be between 2 and 15 characters.")
            );
        }

        if (!user.getFirstName().matches(INVALID_FIRST_LAST_NAME_PATTERN)){
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " first name", user.getFirstName(),
                            "First Name should be only with letters.")
            );
        }

        if (user.getLastName().length() < 2 || user.getLastName().length() > 15) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " last name", user.getLastName(),
                            "Last Name length should be between 2 and 15 characters.")
            );
        }

        if (!user.getLastName().matches(INVALID_FIRST_LAST_NAME_PATTERN)){
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " last name", user.getLastName(),
                            "Last Name should be only with letters.")
            );
        }

        if (user.getUsername().length() < 2 || user.getUsername().length() > 15) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " username", user.getUsername(),
                            "Username length should be between 2 and 15 characters.")
            );
        }

        if (user.getUsername().matches(INVALID_USERNAME_PATTERN)) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " username", user.getUsername(),
                            "Username should be only with letters and number.")
            );
        }

        if (user.getPassword().length() < 6 || user.getPassword().length() > 12) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " password", user.getPassword(),
                            "Password length should be between 6 and 12 characters.")
            );
        }

        if (user.getEmail().length() < 2 || user.getEmail().length() > 15) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), " email", user.getEmail(),
                            "Username length should be between 2 and 50 characters.")
            );
        }

//        if (!user.getEmail().matches(EMAIL_VALIDATION_PATTERN)) {
//            violations.add(
//                    new ConstraintViolation(user.getClass().getName(), " email", user.getEmail(),
//                            "Invalid email address. (et. - ivanov@abv.bg) ")
//            );
//        }

        if (violations.size() > 0) {
            throw new ConstraintViolationException("Invalid entity field", violations);
        }
    }
}
