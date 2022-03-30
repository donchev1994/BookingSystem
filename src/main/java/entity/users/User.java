package entity.users;


import entity.AbstractEntity;
import entity.enums.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.StringJoiner;

@Getter
@Setter
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


        @Override
        public String toString() {
                return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                        .add("id=" + getId())
                        .add("firstName='" + firstName + "'")
                        .add("lastName='" + lastName + "'")
                        .add("username='" + username + "'")
                        .add("password='" + password + "'")
                        .add("email='" + email + "'")
                        .add("registeredDate=" + registeredDate)
                        .add("role=" + role)
                        .toString();
        }
}
