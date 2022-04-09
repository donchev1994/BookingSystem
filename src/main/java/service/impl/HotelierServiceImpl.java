package service.impl;

import dao.HousesRepository;
import dao.RoomRepository;
import dao.UserRepository;
import dao.impl.HousesRepositoryImpl;
import dao.impl.RoomRepositoryImpl;
import entity.hotelAndHouse.HousesProperties;
import entity.hotelAndHouse.Room;
import entity.users.Hotelier;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.HotelierService;
import util.SqlConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class HotelierServiceImpl implements HotelierService {
    private static final String CREATE_HOUSE = "INSERT INTO houses(name, description, stars, address,city_id,propertyType_id) VALUES (?,?,?,?,?,?)";
    private static final String GET_PROPERTY_TYPE = "SELECT * FROM propertytypes";
    private static final String SET_CITY = " houses SET city_id=? WHERE name=?;";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";
    private Connection connection;
    SqlConnector sqlConnector = new SqlConnector();

    {
        try {
            connection = DriverManager.getConnection
                    (CONNECTION_STRING + BOOKING_NAME, "root", "032580");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserRepository userRepository;
    private HousesRepository housesRepository = new HousesRepositoryImpl();
    private RoomRepository roomRepository = new RoomRepositoryImpl();

    public HotelierServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(Hotelier entity) throws InvalidEntityDataException {
        try {
            userRepository.create(entity);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid entity.");
        }
    }

    @Override
    public void update(Hotelier entity) {
        userRepository.update(entity);
    }

    @Override
    public void delete(Hotelier entity) {
        userRepository.delete(entity);
    }


    @Override
    public void addHotelOrGuestHouse(HousesProperties house, int propertyId,int cityId) {
        try (var statement = connection.prepareStatement(CREATE_HOUSE)) {
            statement.setString(1, house.getName());
            statement.setString(2, house.getDescription());
            statement.setInt(3, house.getStars());
            statement.setString(4, house.getAddress());
            statement.setInt(5,cityId);
            statement.setInt(6, propertyId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        try {
            roomRepository.create(room);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

