
package service;


import entity.city.City;
import entity.users.RegisteredUser;
import exception.NonexistentEntityException;

import java.sql.SQLException;
import java.util.Collection;

public interface RegisterUserService extends GenericService<Long, RegisteredUser> {

    String getAllCities() throws NonexistentEntityException;
    String getCityById(Long id);
    String getAllHotelsByCity(String cityName);
    boolean updatePassword(String username, String password) throws SQLException;
    void getTypeAndPriceForRoom(String houseName);
}

