package service.impl;


import dao.CityRepository;
import dao.UserRepository;
import dao.impl.CityRepositoryImpl;
import entity.city.City;
import entity.users.Administrator;
import entity.users.User;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import service.AdministratorService;
import util.SqlConnector;

import java.sql.*;
import java.util.Collection;


public class AdministratorServiceImpl implements AdministratorService {
    private static final String SET_ROLE = "UPDATE users SET role_id = ? WHERE username= ?;";
    private static final String GET_ALL_ROLES = "SELECT * FROM roles";
    private static final String DELETE_USER = "DELETE FROM users WHERE username=?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
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
    private CityRepository cityRepository = new CityRepositoryImpl();


    public AdministratorServiceImpl(UserRepository adminRepo) {
        this.userRepository = adminRepo;

    }

    @Override
    public void addCity(City city) throws SQLException {
        cityRepository.create(city);
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
    public void registerUser(User entity) {
        try {
            userRepository.create(entity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCity(City city) {
        cityRepository.delete(city);
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


    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(Administrator entity) throws SQLException {
        userRepository.create(entity);
    }

    @Override
    public void update(Administrator entity) {
      userRepository.update(entity);
    }

    @Override
    public void delete(Administrator entity) {
        userRepository.delete(entity);
    }


}

