package controller;

import entity.users.RegisteredUser;
import exception.NonexistentEntityException;
import service.RegisterUserService;
import view.Menu;
import view.NewBookingDialog;

import java.util.Collection;
import java.util.List;

public class UserController {
    public RegisterUserService registerUserService;

    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    public void init(){
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
                    var user = new NewBookingDialog().input();
                    var created = registerUserService.save(user);
                    return String.format("User ID:%s: '%s' added successfully.",
                            created.getId(), created.getFirstName());
                })
        ));
        menu.show();
    }
}
