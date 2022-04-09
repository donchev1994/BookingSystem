package dao.impl;

import dao.RoomRepository;
import entity.hotelAndHouse.Room;
import entity.users.RegisteredUser;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class RoomRepositoryImpl  implements RoomRepository {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";
    private static final String CREATE_ROOM = "INSERT INTO rooms (type_of_room, price_per_day) VALUES (?,?);";
    private static final String GET_ALL_ROOM = "SELECT * FROM rooms;";
    private static final String UPDATE_ROOM = "UPDATE rooms SET type_of_room=?,price_per_day=? WHERE id=?;";
    private static final String DELETE_ROOM = "DELETE FROM rooms WHERE id=?;";

    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection
                    (CONNECTION_STRING + BOOKING_NAME, "root", "032580");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Room entity) throws SQLException {
        try (var stmt = connection.prepareStatement(CREATE_ROOM)) {
            stmt.setString(1,entity.getTypeOfRoom().toString());
            stmt.setDouble(2,entity.getPricePerDay());
            stmt.executeUpdate();
        }
    }

    @Override
    public Collection<Room> read() throws NonexistentEntityException {
        try(var stmt = connection.prepareStatement(GET_ALL_ROOM)) {
            var rs = stmt.executeQuery();
            Collection<Room> result = new ArrayList<>();
            while (rs.next()){
                result.add(new Room(
                        rs.getDouble(3)
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error loading rooms");
        }
    }

    @Override
    public void update(Room entity) {
        try(var statement = connection.prepareStatement(UPDATE_ROOM)) {
            statement.setString(1,entity.getTypeOfRoom().toString());
            statement.setDouble(2,entity.getPricePerDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid room property.");
        }
    }

    @Override
    public void delete(Room entity) {
        try(var statement = connection.prepareStatement(DELETE_ROOM)) {
            statement.setInt(1, Math.toIntExact(entity.getId()));
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid id.");
        }
    }
}
