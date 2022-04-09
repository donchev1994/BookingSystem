
import controller.UserController;
import dao.UserRepository;
import dao.impl.UserRepositoryImpl;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Main {

    public static void main(String[] args) throws SQLException {

        UserRepository us = new UserRepositoryImpl();
        RegisterUserService userService = new RegisterUserServiceImpl(us);

        // user login - username: marian123 password: marian123
        var userController = new UserController(userService);
        userController.init();



    }
}

