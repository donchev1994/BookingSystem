package entity.hotelAndHouse;


import entity.enums.Months;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    private Months month;
    private int startDay;
    private int endDay;
}
