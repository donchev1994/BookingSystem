
package util;


import entity.city.City;
import entity.users.RegisteredUser;
import entity.users.User;
import exception.EntityPersistenceException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static entity.enums.Role.USER;

public class SqlConnector {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";
    private static final String GET_ALL_CITIES = "SELECT id,name FROM cities";
    private static final String ADD_CITY = "INSERT INTO cities (name, description) VALUES (?,?)";
    private static final String LOGIN = "SELECT `username`, `password` FROM users WHERE username = ? AND password = ?";
    private static final String REGISTER_USER = "INSERT INTO users (first_name, last_name, username, password, email, registeredDate) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String GET_CITY = "SELECT * FROM cities WHERE id = ?;";
    private static final String GET_ALL_HOTELS = "SELECT * FROM hotels JOIN cities AS c WHERE c.name = ?;";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?;";
    private static final String GET_ROLE = "SELECT r.name FROM users as u JOIN roles r on r.id = u.role_id WHERE username = ?;";
    private static final String SET_ROLE = "UPDATE users SET role_id = ? WHERE username= ?;";
    private static final String GET_ALL_ROLES = "SELECT * FROM roles";
    private static final String PERSONAL_DATA_USERS = "SELECT first_name,last_name,email FROM users WHERE role_id = 5;";

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection
                    (CONNECTION_STRING + BOOKING_NAME, "root", "032580");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getAllCities() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(GET_ALL_CITIES);
        ResultSet resultSet = statement.executeQuery();
        StringBuilder sb = new StringBuilder();


        while (resultSet.next()) {
            sb.append(resultSet.getString("id"));
            sb.append(". ");
            sb.append(resultSet.getString("name"));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void addCity(City city) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(ADD_CITY);
        statement.setString(1, city.getName());
        statement.setString(2, city.getDescription());

        statement.executeUpdate();
    }

    public boolean login(String username, String password) throws SQLException {

        boolean status = false;

        var user = new RegisteredUser();

        PreparedStatement statement = connection.prepareStatement(LOGIN);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet myResult = statement.executeQuery();
        status = myResult.next();

        return status;
    }

    public RegisteredUser registerUser(String firstName, String lastName, String username, String password, String email) {
        var user = new RegisteredUser();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        try {
            PreparedStatement statement = connection.prepareStatement(REGISTER_USER);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, email);
            statement.setString(6, String.valueOf(LocalDateTime.now()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getRole(String username) {
        String role = "";

        try {
            PreparedStatement statement = connection.prepareStatement(GET_ROLE);
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();


            while (rs.next()) {
                role = rs.getString(1);
            }
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    public boolean isUsernameRegistered(String username) {
        String query = "SELECT username FROM users WHERE username=?;";
        return checkIfRegistered(username, query);
    }

    public boolean isEmailRegistered(String email) {
        String query = "SELECT email FROM users WHERE email=?";
        return checkIfRegistered(email, query);
    }

    private boolean checkIfRegistered(String input, String query) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, input);
            ResultSet rs = statement.executeQuery();
            return rs != null;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public String getCity(Long id) {
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

    public String getAllHotels(String cityName) {
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

    public boolean updatePassword(String username, String password) throws SQLException {
        boolean status = false;
        PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD);
        statement.setString(1, password);
        statement.setString(2, username);

        int rs = statement.executeUpdate();

        if (rs > 0) {
            status = true;
        }

        return status;
    }

    public String getRoomsPriceByHotelName(String hotelName) {
        String query = "SELECT h.name, r.type_of_room , r.price_per_day FROM rooms as r\n" +
                "JOIN hotels h on h.id = r.hotel_id";

        return query;
    }

    public boolean updateRole(int role, String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(SET_ROLE);
            statement.setInt(1, role);
            statement.setString(2, username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }

    public void getAllRoles() {
        StringBuilder sb = new StringBuilder();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_ROLES);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                sb.append(rs.getInt(1)).append(" - ")
                        .append(rs.getString(2))
                        .append(System.lineSeparator());
            }
        } catch (SQLException e) {
            throw new EntityPersistenceException("Getting roles failed.");
        }

        System.out.println(sb);
    }

    public void viewPersonalUserData(){
        try {
            PreparedStatement statement = connection.prepareStatement(PERSONAL_DATA_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

