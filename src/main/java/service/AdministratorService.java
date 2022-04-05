package service;

import entity.city.City;
import entity.users.Administrator;

import java.util.Collection;

public interface AdministratorService extends GenericService<Long,Administrator> {

    void addCity(City city);
}
