package dao.impl;

import dao.CityRepository;
import entity.city.City;
import exception.EntityPersistenceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CityRepositoryImpl implements CityRepository {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";
    private static final String ADD_CITY = "INSERT INTO cities (name, description) VALUES (?,?)";
    private static final String GET_ALL_CITIES = "SELECT name ,description FROM cities";
    private static final String DELETE_CITY = "DELETE FROM cities WHERE name=?";
    private static final String UPDATE =   "UPDATE cities SET name=?, description=?;";



    Connection connection;

    {
        try {
            connection = DriverManager.getConnection
                    (CONNECTION_STRING + BOOKING_NAME, "root", "032580");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void  create(City entity) throws SQLException {
        try (var statement = connection.prepareStatement(ADD_CITY)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());

            statement.executeUpdate();
        }
    }

    @Override
    public Collection<City> read() throws EntityPersistenceException {
        try (var statement = connection.prepareStatement(GET_ALL_CITIES)) {
            var rs = statement.executeQuery();
            Collection<City> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new City(
                        rs.getString(1),
                        rs.getString(2)
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error executing SQL query: " + ADD_CITY, e);
        }
    }

    @Override
    public void update(City entity) {
        try(var statement = connection.prepareStatement(UPDATE)){
            statement.setString(1,entity.getName());
            statement.setString(2,entity.getDescription());
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid city properties.");
        }
    }

    @Override
    public void delete(City entity) {
        try (var statement = connection.prepareStatement(DELETE_CITY)) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid city name.");
        }
    }

}
