import controller.UserController;
import dao.AdministratorRepository;
import dao.RegisterUserRepository;
import dao.impl.AdministratorRepositoryImpl;
import dao.impl.RegisterUserRepositoryImpl;
import entity.users.Administrator;
import entity.users.RegisteredUser;
import entity.users.User;
import exception.InvalidEntityDataException;
import exception.NonexistentEntityException;
import service.AdministratorService;
import service.RegisterUserService;
import service.impl.AdministratorServiceImpl;
import service.impl.RegisterUserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static entity.MockUsers.MOCK_USERS;


public class Main {

    public static void main(String[] args) {

        RegisterUserRepository us = new RegisterUserRepositoryImpl();
        RegisterUserService userService = new RegisterUserServiceImpl(us);
        AdministratorRepository as = new AdministratorRepositoryImpl();
        AdministratorService adminService = new AdministratorServiceImpl(as);


        var admin = new Administrator("Mariancho","Donchev","marian","password","email123@abv.bg");

        try {
            adminService.save(admin);
        } catch (InvalidEntityDataException e) {
            e.printStackTrace();
        }

        for (RegisteredUser mockUser : MOCK_USERS) {
            try {
                userService.save(mockUser);
            } catch (InvalidEntityDataException e) {
                e.printStackTrace();
            }
        }

        var userController = new UserController(userService);
        userController.init();
    }
}
