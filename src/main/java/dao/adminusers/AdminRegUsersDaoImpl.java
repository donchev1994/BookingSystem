package dao.adminusers;

import dao.impl.UserRepositoryImpl;
import exception.EntityPersistenceException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRegUsersDaoImpl extends UserRepositoryImpl implements AdminRegUsersDao {
    private static final String GET_ALL_USERS = "SELECT * FROM users WHERE role_id = 5";

    static Connection connection = DatabaseConnection.getConnection();

    @Override
    public void viewAllUsers() {
        StringBuilder sb = new StringBuilder();
        try(var stmt = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                sb.append("ID: ").append(rs.getInt(1)).append(" | ").append(rs.getString("first_name")).append(" | ")
                        .append(rs.getString("last_name")).append(" | username: ").append(rs.getString("username")).append(System.lineSeparator());
            }
            System.out.println(sb);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid command.");
        }
    }
}
