package dao;

import entity.city.City;
import entity.users.Administrator;

public interface AdministratorRepository extends CrudRepository<Long, Administrator> {

    void addCity(City city);
}
