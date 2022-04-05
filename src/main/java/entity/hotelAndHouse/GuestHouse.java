package entity.hotelAndHouse;

import dao.Identifiable;

import entity.city.City;
import entity.enums.PropertyType;
import lombok.NonNull;


import java.util.List;




public class GuestHouse extends Properties implements Identifiable<Long> {

    public GuestHouse(@NonNull String name, @NonNull String description, @NonNull PropertyType status, @NonNull String address, @NonNull City city, @NonNull List<Room> rooms) {
        super(name, description, status, address, city, rooms);
    }


}
