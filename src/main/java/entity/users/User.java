package entity.users;

import dao.Identifiable;
import entity.AbstractEntity;
import entity.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {


        @NonNull
        private String firstName;
        @NonNull
        private String lastName;
        @NonNull
        private String username;
        @NonNull
        private String password;
        @NonNull
        private String email;

        private LocalDate registeredDate = LocalDate.now();

        private Role role;

}
