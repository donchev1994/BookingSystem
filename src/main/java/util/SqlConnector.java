
package util;


import entity.users.RegisteredUser;
import java.sql.*;


public class SqlConnector {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "booking_db";
    private static final String LOGIN = "SELECT `username`, `password` FROM users WHERE username = ? AND password = ?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?;";
    private static final String GET_ROLE = "SELECT r.name FROM users as u JOIN roles r on r.id = u.role_id WHERE username = ?;";
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


    public void viewPersonalUserData(){
        try {
            PreparedStatement statement = connection.prepareStatement(PERSONAL_DATA_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

