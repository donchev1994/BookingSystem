package entity.users;

import dao.Identifiable;
import entity.AbstractEntity;
import entity.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

import static entity.enums.Role.USER;

@NoArgsConstructor
public class RegisteredUser extends User implements Identifiable<Long> {




    public RegisteredUser(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, @NonNull String email) {
        super(firstName, lastName, username, password, email);
        setRole(USER);
    }
}
