import dao.RegisterUserRepository;
import dao.impl.RegisterUserRepositoryImpl;
import entity.users.RegisteredUser;
import entity.users.User;
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

        for (RegisteredUser mockUser : MOCK_USERS) {
            userService.addUser(mockUser);
        }


        RegisteredUser user2 = userService.findById(2L);
        user2.setEmail("gosho");
        userService.updateUser(user2);

        userService.getUsers().forEach(System.out::println);


        System.out.println();
    }
}
