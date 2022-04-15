package controller;

import dao.registeruser.RegisterUserDao;
import dao.registeruser.RegisterUserDaoImpl;
import exception.EntityPersistenceException;
import exception.InvalidEntityDataException;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;
import view.Menu;
import view.NewCommentDialog;
import view.NewPasswordDialog;

import java.util.List;
import java.util.Scanner;


public class RegisterUserController {
    Scanner scanner = new Scanner(System.in);
    RegisterUserDao us = new RegisterUserDaoImpl();
    RegisterUserService userService = new RegisterUserServiceImpl(us);


    public void init() {
        var menu = new Menu("User Menu", List.of(
                new Menu.Option("Cities: ", () -> {
                    try {
                        return String.valueOf(userService.getAllCities());
                    } catch (EntityPersistenceException e) {
                        return e.getMessage();
                    }
                }),
                new Menu.Option("Short description of selected city by ID: ", () -> {
                    System.out.println("Enter ID: ");
                    long ids = 0;
                    while (ids == 0) {
                        long id;
                        try {
                            id = Long.parseLong(scanner.nextLine());
                            if (id <= 0) {
                                System.out.println("ID should be only positive numbers.");
                            } else {
                                ids = id;
                            }
                        } catch (NumberFormatException exception) {
                            System.out.println("ID should be only numbers.");
                        }
                    }

                    try {
                        return userService.getCityById(ids);
                    } catch (EntityPersistenceException exception) {
                        return exception.getMessage();
                    }
                }),
                new Menu.Option("Look all hotels in a city: ", () -> {
                    System.out.println("Enter city name: ");
                    var cityName = scanner.nextLine();
                    return userService.getAllHotelsByCity(cityName);
                }),
                new Menu.Option("Get all rooms by chosen hotel: ", () -> {
                    System.out.println("Enter hotel name: ");
                    var hotelName = scanner.nextLine();
                    try {
                        userService.getTypeAndPriceForRoom(hotelName);
                        return "";
                    } catch (EntityPersistenceException ex){
                       return ex.getMessage();
                    }
                }),
                new Menu.Option("Add comment to hotel: ", () -> {
                    System.out.println("Enter username: ");
                    var user = scanner.nextLine();
                    var id = userService.getId(user);
                    var comment = new NewCommentDialog().input();
                    comment.setUser_id(id);
                    userService.addComment(comment);
                    System.out.println("Enter hotel ID:");
                    var hotelID = Integer.parseInt(scanner.nextLine());
                    var last = userService.getLastCommentId(comment.getDescription());
                    try{
                        userService.writeComment(hotelID,last);
                        return "Successfully added:";
                    }catch (EntityPersistenceException ex){
                        return  ex.getMessage();
                    }
                }),
                new Menu.Option("Change Password: ", () -> {
                    var newPassword = new NewPasswordDialog().input();
                    if (userService.updatePassword(newPassword)) {
                        return "Password changed successfully.";
                    } else {
                        return "Invalid Password";
                    }
                })

        ));
        menu.show();
    }
}
