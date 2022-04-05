package util;


import entity.city.City;
import entity.users.RegisteredUser;
import entity.users.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;

import static entity.enums.Role.USER;

public class SqlConnector {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection
                    (CONNECTION_STRING + BOOKING_NAME, "root","032580");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getAllCities() throws SQLException {
        String query = "SELECT id,name FROM cities";

        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();

        StringBuilder sb = new StringBuilder();


        while (resultSet.next()){
            sb.append(resultSet.getString("id"));
            sb.append(". ");
            sb.append(resultSet.getString("name"));
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    public void addCity(City city) throws SQLException {
        String query = "INSERT INTO cities (name, description) VALUES (?,?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, city.getName());
        statement.setString(2, city.getDescription());

        statement.executeUpdate();
    }

    public void login() throws SQLException{
        String query = "SELECT `username`, `password` FROM " +
                "users WHERE username = ? AND password = ?";
        boolean status = false;

        var user = new RegisteredUser();

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,user.getUsername());
        statement.setString(2,user.getPassword());

        ResultSet myResult = statement.executeQuery();
        status = myResult.next();

    }

    public User registerUser(String firstName,String lastName,String username,String password, String email){
        var user = new User();

        String query = "INSERT INTO users " +
                "(first_name, last_name, username, password, email, registeredDate,role ) " +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,username);
            statement.setString(4,password);
            statement.setString(5,email);
            statement.setString(6,String.valueOf(LocalDate.now()));
            statement.setString(7,USER.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
