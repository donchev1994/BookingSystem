package view;


import controller.*;

import dao.impl.LoginDao;

import java.sql.SQLException;
import java.util.Scanner;

import static entity.enums.Role.*;


public class LoginDialog implements EntityDialog<String > {
    private static Scanner scanner = new Scanner(System.in);
    private LoginDao connector = new LoginDao();



    @Override
    public String  input() throws SQLException {
        System.out.println("Username: ");
        var username = scanner.nextLine();
        System.out.println("Password: ");
        var password = scanner.nextLine();

        if(connector.login(username,password)){
            if(connector.getRole(username).equals(USER.toString())){
                var userController = new RegisterUserController();
                userController.init();
            } else if(connector.getRole(username).equals(ADMIN.toString())){
                var adminController = new AdministratorController();
                adminController.init();
            } else if(connector.getRole(username).equals(ADMIN_USER.toString())){
                var adminUserController = new AdminUsersController();
                adminUserController.init();
            } else if(connector.getRole(username).equals(ADMIN_HOTELIER.toString())){
                var adminHotelierController = new AdminHoteliersController();
                adminHotelierController.init();
            } else if(connector.getRole(username).equals(HOTELIER.toString())){
                var hotelierController = new HoteliersController();
                hotelierController.init();
            }
            System.out.println("Login successfully.");
            return "";
        } else {
            return "Invalid username or password.";
        }
    }
}
