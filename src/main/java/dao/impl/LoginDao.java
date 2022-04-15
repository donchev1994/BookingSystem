
package dao.impl;

import util.DatabaseConnection;

import java.sql.*;


public class LoginDao {

    private static final String LOGIN = "SELECT `username`, `password` FROM users WHERE username = ? AND password = ?";
    private static final String GET_ROLE = "SELECT r.name FROM users as u JOIN roles r on r.id = u.role_id WHERE username = ?;";
    private static final String PERSONAL_DATA_USERS = "SELECT first_name,last_name,email FROM users WHERE role_id = 5;";

    static Connection connection = DatabaseConnection.getConnection();

    public boolean login(String username, String password) {

        boolean status = false;

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(LOGIN);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet myResult = statement.executeQuery();
            status = myResult.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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


    public void viewPersonalUserData(){
        try {
            PreparedStatement statement = connection.prepareStatement(PERSONAL_DATA_USERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

