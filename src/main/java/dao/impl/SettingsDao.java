package dao.impl;


import dao.registeruser.RegisterUserDao;
import dao.registeruser.RegisterUserDaoImpl;
import entity.enums.Role;
import entity.users.User;
import exception.EntityPersistenceException;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingsDao {
    RegisterUserDao us = new RegisterUserDaoImpl();
    RegisterUserService userService = new RegisterUserServiceImpl(us);

    private static final String GET_USER_BY_USERNAME = "SELECT first_name, last_name, username, password, email, r.name FROM users u\n" +
            "JOIN roles r on r.id = u.role_id\n" +
            "WHERE username = ?;";
    static Connection connection = DatabaseConnection.getConnection();



    public User getUserSettings(String username){
        var user = new User();
        try(var stmt = connection.prepareStatement(GET_USER_BY_USERNAME)){
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                user.setFirstName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setUsername(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setRole(Role.valueOf(rs.getString("r.name")));

            }
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid username:");
        }

        return user;
    }

}
