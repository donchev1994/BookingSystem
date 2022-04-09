
package controller;

import service.RegisterUserService;
import view.LoginDialog;
import view.Menu;
import view.NewRegisteredUserDialog;

import java.util.List;


public class UserController {

    private RegisterUserService registerUserService;


    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }


    public void init() {
        var menu = new Menu("Booking Menu", List.of(
                new Menu.Option("Login", () -> {
                    var loginDialog = new LoginDialog();
                    return loginDialog.input();
                }),
                new Menu.Option("Register", () -> {
                    var user = new NewRegisteredUserDialog().input();
                    registerUserService.save(user);
                    return String.format("User '%s' added successfully.",
                            user.getFirstName());
                })
        ));
        menu.show();
    }


}

