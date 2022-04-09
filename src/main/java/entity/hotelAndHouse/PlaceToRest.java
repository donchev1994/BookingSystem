
package entity.hotelAndHouse;

import entity.city.City;
import entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceToRest {
    private long id;
    private String name;
    private String description;
    private String tag;
    private Status status;
    private long sleepingPlaces;
    private int stars;
    private City city;
}
