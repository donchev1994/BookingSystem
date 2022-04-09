package entity.users;

import dao.Identifiable;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor
public class Hotelier extends User implements Identifiable<Long> {

    public Hotelier(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, @NonNull String email) {
        super(firstName, lastName, username, password, email);
    }
}
