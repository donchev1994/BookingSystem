package entity.users;

import dao.Identifiable;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdminHoteliers extends User implements Identifiable<Long> {

    private List<Hotelier> hoteliers = new ArrayList<>();

}
