package dao.impl;

import dao.AdministratorRepository;
import entity.city.City;
import entity.users.Administrator;
import util.SqlConnector;

import java.sql.SQLException;

public class AdministratorRepositoryImpl extends CrudRepositoryImpl<Long,Administrator> implements AdministratorRepository {


    SqlConnector connector = new SqlConnector();

    @Override
    public void addCity(City city) {
        try {
            connector.addCity(city);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
