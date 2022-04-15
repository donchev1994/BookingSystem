package dao.administrator;

import dao.impl.UserRepositoryImpl;
import exception.EntityPersistenceException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDaoImpl extends UserRepositoryImpl implements AdministratorDao {
    private static final String SET_ROLE = "UPDATE users SET role_id = ? WHERE username= ?;";
    private static final String GET_ALL_ROLES = "SELECT * FROM roles";
    private static final String DELETE_USER = "DELETE FROM users WHERE username=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";


    Connection connection = DatabaseConnection.getConnection();

    public AdministratorDaoImpl() {
    }

    public AdministratorDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void updateRole(int role, String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(SET_ROLE);
            statement.setInt(1, role);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid username or role id.");
        }
    }

    @Override
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




    @Override
    public boolean deleteUser(String username) {
        try(var statement = connection.prepareStatement(DELETE_USER)) {
            statement.setString(1,username);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid username.");
        }
    }
}
