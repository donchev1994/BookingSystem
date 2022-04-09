package entity.hotelAndHouse;

import entity.AbstractEntity;
import entity.Comments.Comment;
import entity.city.City;
import entity.enums.PropertyType;
import lombok.*;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class HousesProperties extends AbstractEntity{

    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private PropertyType propertyType;
    private int stars;
    @NonNull
    private String address;
    private City city;
    List<Room> rooms;
    List<Comment> comments;

    public HousesProperties(@NonNull String name, @NonNull String description, int stars, @NonNull String address) {
        this.name = name;
        this.description = description;
        this.stars = stars;
        this.address = address;
    }
}
