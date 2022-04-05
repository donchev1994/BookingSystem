package util;


import entity.city.City;
import entity.users.User;

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

    public boolean login(String username, String password) throws SQLException{

        boolean flag = false;
        Statement statement = connection.createStatement();
        ResultSet myResult = statement.executeQuery(
                "SELECT username,password FROM users WHERE username = '" + username + "'" +
                " AND password = '" + password + "'");

        if(myResult.next()){
            flag =true;
        } else {
            System.out.println("Invalid");
        }
//        User user = null;
//
//        if(myResult.next()){
//            user = new User();
//            user.setUsername(username);
//        }
//
//        return user;

        return flag;
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
