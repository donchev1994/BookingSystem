
package entity.users;

import dao.Identifiable;
import lombok.*;

import static entity.enums.Role.USER;

@NoArgsConstructor
public class RegisteredUser extends User implements Identifiable<Long> {


    public RegisteredUser(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, @NonNull String email) {
        super(firstName, lastName, username, password, email);
        setRole(USER);
    }

}
