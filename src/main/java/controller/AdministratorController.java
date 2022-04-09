package controller;

import dao.AdministratorRepository;
import dao.impl.AdministratorRepositoryImpl;
import entity.enums.Role;
import service.AdministratorService;
import service.impl.AdministratorServiceImpl;
import view.Menu;
import view.NewCityDialog;
import view.NewRegisteredUserDialog;

import java.util.List;
import java.util.Scanner;

public class AdministratorController {
    Scanner scanner = new Scanner(System.in);
    AdministratorRepository ar = new AdministratorRepositoryImpl();
    AdministratorService administratorService = new AdministratorServiceImpl(ar);

    public void init() {
        var menu = new Menu("Admin Menu", List.of(
                new Menu.Option("Add City: ", () -> {
                    var city = new NewCityDialog().input();
                    administratorService.addCity(city);
                    return "City " + city.getName() + "is added correctly";
                }),
                new Menu.Option("Update user role: ", () -> {
                    System.out.println("Enter username:");
                    var username = scanner.nextLine();
                    administratorService.getAllRoles();
                    System.out.println("Enter new role by id:");
                    var role = Integer.parseInt(scanner.nextLine());
                    administratorService.updateRole(role, username);
                    return "";
                }),
                new Menu.Option("Register User: ", () -> {
                    var user = new NewRegisteredUserDialog().input();
                    administratorService.registerUser(user);
                    return String.format("User '%s' added successfully.",
                            user.getFirstName());
                })
        ));
        menu.show();
    }
}
