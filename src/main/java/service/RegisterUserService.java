
package service;


import entity.users.RegisteredUser;

import java.sql.SQLException;

public interface RegisterUserService extends GenericService<Long, RegisteredUser> {

    String getAllCities();
    String getCityById(Long id);
    String getAllHotelsByCity(String cityName);
    boolean updatePassword(String username, String password) throws SQLException;
}

