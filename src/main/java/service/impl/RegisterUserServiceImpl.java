
package service.impl;

import dao.CityRepository;
import dao.UserRepository;
import dao.impl.CityRepositoryImpl;
import entity.city.City;
import entity.users.RegisteredUser;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import service.RegisterUserService;
import util.SqlConnector;


import java.sql.*;
import java.util.Collection;


public class RegisterUserServiceImpl implements RegisterUserService {
    private static final String GET_ALL_HOTELS = "SELECT * FROM houses JOIN cities AS c WHERE c.name = ?;";
    private static final String GET_ALL_CITIES = "SELECT id,name FROM cities";
    private static final String GET_CITY = "SELECT * FROM cities WHERE id = ?;";
    private static final String SELECT_ROOMS = "SELECT r.id,r.type_of_room,r.price_per_day FROM houses h " +
            "JOIN rooms r on h.id = r.houses_id " +
            "WHERE h.name= ?;";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";
    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection
                    (CONNECTION_STRING + BOOKING_NAME, "root", "032580");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private UserRepository userRepository;
    private SqlConnector connector = new SqlConnector();

    public RegisterUserServiceImpl(UserRepository registerRepo) {
        this.userRepository = registerRepo;
    }


    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(RegisteredUser entity) throws SQLException {
        userRepository.create(entity);
    }

    @Override
    public void update(RegisteredUser entity) {
        userRepository.update(entity);
    }


    @Override
    public void delete(RegisteredUser entity) {
         userRepository.delete(entity);
    }


    @Override
    public String getAllCities() {
        StringBuilder sb = new StringBuilder();
        try (var statement = connection.prepareStatement(GET_ALL_CITIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sb.append(resultSet.getString("id"));
                sb.append(". ");
                sb.append(resultSet.getString("name"));
                sb.append(System.lineSeparator());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    @Override
    public String getCityById(Long id) {
        StringBuilder sb = new StringBuilder();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_CITY);
            statement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sb.append("You chose city with id '").append(id).append("'")
                        .append(System.lineSeparator());
                sb.append(resultSet.getString("name"))
                        .append(System.lineSeparator());
                sb.append("Description:")
                        .append(System.lineSeparator());
                sb.append(resultSet.getString("description"));
            }

            return sb.toString();

        } catch (SQLException e) {
            return "Invalid City id";
        }
    }

    @Override
    public String getAllHotelsByCity(String cityName) {
        StringBuilder sb = new StringBuilder();

        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_HOTELS);
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sb.append("Name - ");
                sb.append(resultSet.getString("name"));
                sb.append(" | ");
                sb.append(System.lineSeparator());
                sb.append("Description - ");
                sb.append(resultSet.getString("description"));
                sb.append(" | ");
                sb.append(System.lineSeparator());
                sb.append("Address - ");
                sb.append(resultSet.getString("address"));
                sb.append(" | ");
                sb.append(System.lineSeparator());
                sb.append("---------------------");
                sb.append(System.lineSeparator());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sb.toString();

    }

    @Override
    public boolean updatePassword(String username, String password) throws SQLException {
        return connector.updatePassword(username, password);
    }

    @Override
    public void getTypeAndPriceForRoom(String houseName) {
        StringBuilder sb = new StringBuilder();
        try(var stmt = connection.prepareStatement(SELECT_ROOMS)) {
            stmt.setString(1,houseName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                sb.append(rs.getInt(1)).append(".  ")
                        .append(rs.getString(2).toLowerCase())
                        .append(" room")
                        .append(" - ")
                        .append(rs.getDouble(3))
                        .append("lv.")
                        .append(System.lineSeparator());
            }
            System.out.println(sb);

        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid house name.");
        }
    }
}
