package entity.users;

import dao.Identifiable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;




public class Administrator extends User implements Identifiable<Long> {


    public Administrator(@NonNull String firstName, @NonNull String lastName, @NonNull String username, @NonNull String password, @NonNull String email) {
        super(firstName, lastName, username, password, email);
    }

}
