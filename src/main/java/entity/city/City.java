package entity.city;


import entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntity{

    private String name;
    private String description;

}
