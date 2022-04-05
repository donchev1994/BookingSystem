package entity.users;

import dao.Identifiable;
import entity.enums.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegUsers extends User implements Identifiable<Long> {

    private List<RegisteredUser> users = new ArrayList<>();

    @Override
    public void setRole(Role role) {
        super.setRole(role);
    }
}
