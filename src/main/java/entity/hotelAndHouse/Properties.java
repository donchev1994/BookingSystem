package entity.hotelAndHouse;

import entity.AbstractEntity;
import entity.Comments.Comment;
import entity.city.City;
import entity.enums.PropertyType;
import lombok.*;

import java.util.List;
import java.util.Map;



@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Properties extends AbstractEntity{

    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull

    private PropertyType propertyType;
    private int stars;
    @NonNull
    private String address;
    @NonNull
    private City city;
    @NonNull
    List<Room> rooms;
    List<Comment> comments;

}
