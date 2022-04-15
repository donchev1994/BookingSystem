package dao.impl;

import dao.RoomRepository;
import entity.hotelAndHouse.Room;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class RoomRepositoryImpl  implements RoomRepository {

    private static final String CREATE_ROOM = "INSERT INTO rooms (type_of_room, price_per_day) VALUES (?,?);";
    private static final String GET_ALL_ROOM = "SELECT * FROM rooms;";
    private static final String UPDATE_ROOM = "UPDATE rooms SET type_of_room=?,price_per_day=? WHERE id=?;";
    private static final String DELETE_ROOM = "DELETE FROM rooms WHERE id=?;";

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public void create(Room entity)  {
        try (var stmt = connection.prepareStatement(CREATE_ROOM)) {
            stmt.setString(1,entity.getTypeOfRoom().toString());
            stmt.setDouble(2,entity.getPricePerDay());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean update(Room entity) {
        try(var statement = connection.prepareStatement(UPDATE_ROOM)) {
            statement.setString(1,entity.getTypeOfRoom().toString());
            statement.setDouble(2,entity.getPricePerDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid room property.");
        }
        return false;
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
