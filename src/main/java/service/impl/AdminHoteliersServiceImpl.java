
package service.impl;


import dao.UserRepository;
import entity.users.AdminHoteliers;
import entity.users.User;
import exception.ConstraintViolationException;
import exception.EntityPersistenceException;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdminHoteliersService;
import util.UserValidator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


public class AdminHoteliersServiceImpl implements AdminHoteliersService {
    private static final String GET_ALL_HOTELIERS = "SELECT * FROM users WHERE role_id = 4";
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

    public AdminHoteliersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAll() throws NonexistentEntityException {
        return userRepository.read();
    }

    @Override
    public void save(AdminHoteliers entity) throws InvalidEntityDataException, SQLException {
        userRepository.create(entity);
    }

    @Override
    public void update(AdminHoteliers entity) {
        userRepository.update(entity);
    }

    @Override
    public void delete(AdminHoteliers entity) {
        userRepository.delete(entity);
    }


    @Override
    public void getAllHoteliers() {
        StringBuilder stringBuilder = new StringBuilder();
        try(var stmt = connection.prepareStatement(GET_ALL_HOTELIERS)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                stringBuilder.append("ID: ").append(rs.getInt(1)).append(" | ").append(rs.getString("first_name")).append(" | ")
                        .append(rs.getString("last_name")).append(" | username: ").append(rs.getString("username")).append(System.lineSeparator());
            }
            System.out.println(stringBuilder);
        } catch (SQLException e) {
            throw new EntityPersistenceException("Invalid command.");
        }
    }
}
