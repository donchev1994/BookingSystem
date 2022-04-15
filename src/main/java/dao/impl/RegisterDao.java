package dao.impl;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {

    static Connection connection = DatabaseConnection.getConnection();

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
}
