package entity;


import dao.Identifiable;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractEntity implements Serializable, Identifiable<Long> {


    private static long nextID = 0;
    private Long id = ++nextID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
