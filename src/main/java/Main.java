
import controller.UserController;
import dao.registeruser.RegisterUserDao;
import dao.registeruser.RegisterUserDaoImpl;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;

import java.sql.SQLException;



public class Main {

    public static void main(String[] args) throws SQLException {

        RegisterUserDao us = new RegisterUserDaoImpl();
        RegisterUserService userService = new RegisterUserServiceImpl(us);

        // user login - username: vankata94 password: Vankata94!
        // admin login - username: admin password: admin
        var userController = new UserController(userService);
        userController.init();



    }
}

