package controller;

import dao.administrator.AdministratorDao;
import dao.administrator.AdministratorDaoImpl;
import entity.users.User;
import service.AdministratorService;
import service.impl.AdministratorServiceImpl;
import view.Menu;
import view.NewCityDialog;
import view.NewRegisteredUserDialog;

import java.util.List;
import java.util.Scanner;

public class AdministratorController {
    Scanner scanner = new Scanner(System.in);
    AdministratorDao ar = new AdministratorDaoImpl();
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
                }),
                new Menu.Option("Delete user by username: ", () -> {
                    System.out.println("Enter username:");
                    var username = scanner.nextLine();
                    if(administratorService.deleteUser(username)){
                        return "User with '" + username + "' successfully deleted";
                    } else {
                        return "Invalid username.";
                    }
                }),
                new Menu.Option("View all users: ", () -> {
                    StringBuilder sb= new StringBuilder();
                    for (User user : administratorService.getAll()) {
                        sb.append(user).append(System.lineSeparator());
                    }
                    return sb.toString();
                })

        ));

        menu.show();
    }
}
