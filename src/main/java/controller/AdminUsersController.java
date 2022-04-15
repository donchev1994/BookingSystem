package controller;


import dao.adminusers.AdminRegUsersDao;
import dao.adminusers.AdminRegUsersDaoImpl;
import service.AdminRegUsersService;
import service.impl.AdminRegUsersServiceImpl;

import view.Menu;

import java.util.List;
import java.util.Scanner;

public class AdminUsersController {

    Scanner scanner = new Scanner(System.in);
    AdminRegUsersDao ar = new AdminRegUsersDaoImpl();
    AdminRegUsersService adminRegUsersService = new AdminRegUsersServiceImpl(ar);

    public void init() {
        var menu = new Menu("Admin User Menu", List.of(
                new Menu.Option("Get All Users", () -> {
                    adminRegUsersService.viewAllUsers();
                    return "Command successfully";
                })
        ));
        menu.show();
    }

}
