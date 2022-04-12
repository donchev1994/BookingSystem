package dao.impl;

import dao.HousesRepository;
import entity.hotelAndHouse.HousesProperties;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class HousesRepositoryImpl implements HousesRepository {

    private static final String CREATE_HOUSE = "INSERT INTO houses(name, description, stars, address,) VALUES (?,?,?,?)";
    private static final String GET_ALL_HOUSES = "SELECT h.name,h.description,stars,address FROM houses h";
    private static final String DELETE_HOUSE = "DELETE FROM house WHERE name=?";

    static Connection connection = DatabaseConnection.getConnection();



    @Override
    public void create(HousesProperties entity) throws SQLException {
        try(var statement = connection.prepareStatement(CREATE_HOUSE)){
            statement.setString(1,entity.getName());
            statement.setString(2,entity.getDescription());
            statement.setInt(3,entity.getStars());
            statement.setString(4,entity.getAddress());
            statement.executeQuery();
        }
    }

    @Override
    public Collection<HousesProperties> read() throws NonexistentEntityException {
        try(var stmt = connection.prepareStatement(GET_ALL_HOUSES)){
            var rs = stmt.executeQuery();
            Collection<HousesProperties> result = new ArrayList<>();
            while (rs.next()){
                result.add(new HousesProperties(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error executing SQL query: " + GET_ALL_HOUSES,e);
        }
    }

    @Override
    public void update(HousesProperties entity) {

    }

    @Override
    public void delete(HousesProperties entity) {
        try (var stmt = connection.prepareStatement(DELETE_HOUSE)) {
            stmt.setString(1,entity.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid entity");
        }
    }
}
