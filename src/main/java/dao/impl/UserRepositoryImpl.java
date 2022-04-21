package dao.impl;


import dao.CrudRepository;
import dao.Identifiable;
import dao.UserRepository;
import entity.users.User;
import exception.EntityPersistenceException;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;


public  class UserRepositoryImpl implements CrudRepository<Long,User>,UserRepository {
    private static final String REGISTER_USER = "INSERT INTO users (first_name, last_name, username, password, email, registeredDate ) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String SELECT_ALL_USERS = "SELECT u.first_name,u.last_name,u.username,u.password,u.registeredDate,r.name FROM users as u\n" +
            "JOIN roles r on r.id = u.role_id;";
    private static final String UPDATE_USER = "UPDATE users SET first_name=?,last_name=?,password=?,email=? WHERE username=?;";
    private static final String DELETE_USER = "DELETE FROM users WHERE username=?";

    private static final String GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username=?";

    Connection conn = DatabaseConnection.getConnection();

    public UserRepositoryImpl() {
    }

    public UserRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(User entity) {

        try (var stmt = conn.prepareStatement(REGISTER_USER)) {

            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setString(3, entity.getUsername());
            stmt.setString(4, entity.getPassword());
            stmt.setString(5, entity.getEmail());
            stmt.setString(6, String.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error creating user");
        }

    }


    @Override
    public Collection<User> read() throws EntityPersistenceException {
        try(var stmt = conn.prepareStatement(SELECT_ALL_USERS)){
            var rs = stmt.executeQuery();
            Collection<User> result = new ArrayList<>();
            while (rs.next()){
                result.add(new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Error executing SQL query: " + SELECT_ALL_USERS,e);
        }
    }

    @Override
    public boolean update(User entity) {
        try(var statement = conn.prepareStatement(UPDATE_USER)) {
            statement.setString(1,entity.getFirstName());
            statement.setString(2,entity.getLastName());
            statement.setString(3,entity.getPassword());
            statement.setString(4,entity.getEmail());
            statement.setString(5,entity.getUsername());
            statement.executeUpdate();
//            return true;
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid entity");
        }

        return true;
    }

    @Override
    public void delete(User entity) {
        try(var statement = conn.prepareStatement(DELETE_USER)){
            statement.setString(1,entity.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid user for delete");
        }
    }

    public User getUserByUsername(String username) {
        User user = new User();
        try (var stmt = conn.prepareStatement(GET_USER_BY_USERNAME)){
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setEmail(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
