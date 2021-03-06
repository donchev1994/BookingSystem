package controller;

import dao.adminhoteliers.AdminHoteliersDao;
import dao.adminhoteliers.AdminHoteliersDaoImpl;
import service.AdminHoteliersService;
import service.impl.AdminHoteliersServiceImpl;
import view.Menu;

import java.util.List;
import java.util.Scanner;

public class AdminHoteliersController {
    Scanner scanner = new Scanner(System.in);
    AdminHoteliersDao ar = new AdminHoteliersDaoImpl();
    AdminHoteliersService hotelierService = new AdminHoteliersServiceImpl(ar);

     public void init() {
        var menu = new Menu("Admin User Menu", List.of(
                new Menu.Option("Get All Hoteliers", () -> {
                    hotelierService.getAllHoteliers();
                    return "Command successfully";
                })
        ));
        menu.show();
    }
}
