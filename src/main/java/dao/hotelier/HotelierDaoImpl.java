package dao.hotelier;

import dao.impl.UserRepositoryImpl;
import entity.hotelAndHouse.HousesProperties;
import entity.hotelAndHouse.Room;
import exception.EntityPersistenceException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelierDaoImpl extends UserRepositoryImpl implements HotelierDao {

    private static final String CREATE_HOUSE = "INSERT INTO houses(name, description, stars, address,city_id,propertyType_id) VALUES (?,?,?,?,?,?)";
    private static final String GET_PROPERTY_TYPE = "SELECT * FROM propertytypes";
    private static final String GET_CITIES_ID = "SELECT id,name FROM cities;";
    private static final String SET_CITY = " houses SET city_id=? WHERE name=?;";

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public void addHotelOrGuestHouse(HousesProperties house, int propertyId, int cityId) {
        try (var statement = connection.prepareStatement(CREATE_HOUSE)) {
            statement.setString(1, house.getName());
            statement.setString(2, house.getDescription());
            statement.setInt(3, house.getStars());
            statement.setString(4, house.getAddress());
            statement.setInt(5,cityId);
            statement.setInt(6, propertyId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid properties.");
        }
    }

    @Override
    public void selectCities(){
        try(var statement = connection.prepareStatement(GET_CITIES_ID)){
            StringBuilder sb = new StringBuilder();
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                sb.append(rs.getInt(1))
                        .append(" - ")
                        .append(rs.getString(2))
                        .append(System.lineSeparator());
            }

            System.out.println(sb);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid table.");
        }
    }

    @Override
    public void selectPropertyId() {
        try (var stmt = connection.prepareStatement(GET_PROPERTY_TYPE)) {
            StringBuilder sb = new StringBuilder();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sb.append(rs.getInt(1)).append(" - ").append(rs.getString(2)).append(System.lineSeparator());
            }
            System.out.println(sb);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid table.");
        }
    }

    @Override
    public void addRoomsToHotel(Room room) {

    }
}
