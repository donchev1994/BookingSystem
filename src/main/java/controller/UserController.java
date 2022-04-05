package controller;

import entity.users.RegisteredUser;
import exception.NonexistentEntityException;
import service.RegisterUserService;
import util.SqlConnector;
import view.Menu;
import view.NewRegisteredUserDialog;
import view.NewCityDialog;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class UserController {

    public RegisterUserService registerUserService;
    private SqlConnector connector = new SqlConnector();


    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }


    public void init() {
        var menu = new Menu("User Menu", List.of(
                new Menu.Option("Load Users", () -> {
                    System.out.println("Loading users ...");
//                    registerUserService.loadData();
                    return "Users loaded successfully.";
                }),
                new Menu.Option("Print All Users", () -> {
                    Collection<RegisteredUser> users = null;
                    try {
                        users = registerUserService.getAll();
                    } catch (NonexistentEntityException e) {
                        e.printStackTrace();
                    }
                    users.forEach(System.out::println);
                    return "Total user count: " + users.size();
                }),
                new Menu.Option("Add New User", () -> {
                    var user = new NewRegisteredUserDialog().input();
                    connector.registerUser(user.getFirstName(),user.getLastName(),user.getUsername(),user.getPassword(),user.getEmail());
//                    var created = registerUserService.save(user);
                    return String.format("User ID:%s: '%s' added successfully.",
                            user.getId(), user.getFirstName());
                }),
                new Menu.Option("Get all cities", () -> {
                    return connector.getAllCities();
                }),
                new Menu.Option("Add city", () -> {
                    var city = new NewCityDialog().input();
                    connector.addCity(city);
                    return "City " + city.getName() + "is added correctly";
                })

        ));
        menu.show();
    }


}
