
package service;

import entity.city.City;
import entity.users.Administrator;
import entity.users.RegisteredUser;

public interface AdministratorService extends GenericService<Long,Administrator> {

    void addCity(City city);
    void updateRole(int role,String username);
    void getAllRoles();
    RegisteredUser registerUser(RegisteredUser entity);
}

