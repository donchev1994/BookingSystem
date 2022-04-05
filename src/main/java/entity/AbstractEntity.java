package entity;


import dao.Identifiable;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractEntity implements Serializable, Identifiable<Long> {


    private static long nextID = 0;
    private Long id = ++nextID;

}
