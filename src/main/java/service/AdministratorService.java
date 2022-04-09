
package service;

import entity.city.City;
import entity.users.Administrator;
import entity.users.User;

import java.sql.SQLException;

public interface AdministratorService extends GenericService<Long,Administrator> {

    void addCity(City city) throws SQLException;
    void updateRole(int role,String username);
    void getAllRoles();
    void registerUser(User entity);
    void removeCity(City city);
    boolean deleteUser(String username);
}

