package service;

import entity.hotelAndHouse.HousesProperties;
import entity.hotelAndHouse.Room;
import entity.users.Hotelier;
import entity.users.User;

import java.util.Collection;

public interface HotelierService extends GenericService<Long,Hotelier>{
    void addHotelOrGuestHouse(HousesProperties house,int propertyId,int cityId);
    void selectPropertyId();
    void addRoomsToHotel(Room room);
    void selectCities();

    void getCities();
}
