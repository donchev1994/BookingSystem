import controller.UserController;
import dao.RegisterUserRepository;
import dao.impl.RegisterUserRepositoryImpl;
import entity.users.RegisteredUser;
import entity.users.User;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static entity.MockUsers.MOCK_USERS;


public class Main {

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String BOOKING_NAME = "bookings_db";

    public void setConnection(String user, String password) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        Connection connection = DriverManager.getConnection(CONNECTION_STRING + BOOKING_NAME, props);
    }

    public static void main(String[] args) {

        RegisterUserRepository us = new RegisterUserRepositoryImpl();
        RegisterUserService userService = new RegisterUserServiceImpl(us);

        // Тест на един Generic Service

        for (RegisteredUser mockUser : MOCK_USERS) {
            try {
                userService.save(mockUser);
            } catch (InvalidEntityDataException e) {
                e.printStackTrace();
            }
        }


        var first = new RegisteredUser("Marian24","Donchev","marian","password","email");

        try {
            userService.save(first);
        } catch (InvalidEntityDataException e) {
            e.printStackTrace();
        }


        System.out.println();

        var user2 = new RegisteredUser();

        try {
            user2 = userService.findById(1L);
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
        }


        user2.setEmail("gosho");
        userService.update(user2);

        try {
            userService.getAll().forEach(System.out::println);
        } catch (NonexistentEntityException e) {
            e.printStackTrace();
        }

        var userController = new UserController(userService);
        userController.init();


        System.out.println();



    }
}
