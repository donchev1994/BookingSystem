package entity.users;

import dao.Identifiable;
import entity.enums.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Administrator extends User implements Identifiable<Long> {

    private Map<Long,AdminHoteliers> adminHoteliers;
    private Map<Long,AdminRegUsers> adminRegUsers;
    private Role role = Role.ADMINISTRATOR;

    public Administrator(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, @NonNull String email) {
        super(firstName, lastName, username, password, email);

    }
}
