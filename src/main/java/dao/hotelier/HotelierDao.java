package dao.hotelier;

import dao.UserRepository;
import entity.hotelAndHouse.HousesProperties;
import entity.hotelAndHouse.Room;


public interface HotelierDao extends UserRepository {
    void addHotelOrGuestHouse(HousesProperties house, int propertyId, int cityId);
    void selectPropertyId();
    void addRoomsToHotel(Room room);
    void selectCities();
}
