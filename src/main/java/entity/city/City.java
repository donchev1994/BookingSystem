
package entity.city;


import entity.AbstractEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends AbstractEntity{

    private String name;
    private String description;

}

