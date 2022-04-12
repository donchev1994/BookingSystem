package controller;

import dao.UserRepository;
import dao.impl.UserRepositoryImpl;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;
import view.Menu;
import view.NewPasswordDialog;

import java.util.List;
import java.util.Scanner;

import static java.lang.Long.valueOf;

public class RegisterUserController {
    Scanner scanner = new Scanner(System.in);
    UserRepository us = new UserRepositoryImpl();
    RegisterUserService userService = new RegisterUserServiceImpl(us);


    public void init() {
        var menu = new Menu("User Menu", List.of(
                new Menu.Option("Cities: ", () -> {
                    return String.valueOf(userService.getAllCities());
                }),
                new Menu.Option("Short description of selected city by ID: ", () -> {
                    System.out.println("Enter ID: ");
                    var id = scanner.nextLine();
                    return userService.getCityById(valueOf(id));
                }),
                new Menu.Option("Look all hotels in a city: ", () -> {
                    System.out.println("Enter city name: ");
                    var cityName = scanner.nextLine();
                    return userService.getAllHotelsByCity(cityName);
                }),
                new Menu.Option("Get all rooms by chosen hotel: ", () -> {
                    System.out.println("Enter hotel name: ");
                    var hotelName = scanner.nextLine();
                    userService.getTypeAndPriceForRoom(hotelName);
                    return "";
                }),
                new Menu.Option("Change Password: ", () -> {
                    System.out.println("Enter username:");
                    var username = scanner.nextLine();
                    var newPassword = new NewPasswordDialog().input();
                    if (userService.updatePassword(username, newPassword)) {
                        return "Password changed successfully.";
                    } else {
                        return "Invalid Password";
                    }
                })

        ));
        menu.show();
    }
}