
package service.impl;


import dao.CrudRepository;
import dao.UserRepository;
import entity.users.AdminRegUsers;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdminRegUsersService;
import util.SqlConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


public class AdminRegUsersServiceImpl implements AdminRegUsersService {
    private static final String GET_ALL_USERS = "SELECT * FROM users WHERE role_id = 5";
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

    public AdminRegUsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(AdminRegUsers entity) throws InvalidEntityDataException {
        try {
            userRepository.create(entity);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid entity.");
        }
    }

    @Override
    public void update(AdminRegUsers entity) {
        userRepository.update(entity);
    }

    @Override
    public void delete(AdminRegUsers entity) {
        userRepository.delete(entity);
    }


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
