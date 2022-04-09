package entity.hotelAndHouse;


import dao.Identifiable;
import entity.AbstractEntity;
import entity.enums.TypeOfRoom;
import lombok.*;

//import javax.persistence.*;
//import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends AbstractEntity implements Identifiable<Long> {

    private TypeOfRoom typeOfRoom;
    private double pricePerDay;
    //Array date to/from reservation
    List<Reservation> reservations;

    public Room(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}
