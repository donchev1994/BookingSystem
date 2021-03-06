package controller;

import dao.hotelier.HotelierDao;
import dao.hotelier.HotelierDaoImpl;
import exception.EntityPersistenceException;
import exception.NonexistentEntityException;
import service.HotelierService;
import service.impl.HotelierServiceImpl;
import view.Menu;
import view.NewHouseDialog;
import view.NewRoomDialog;



import java.util.List;
import java.util.Scanner;


public class HoteliersController {
    Scanner scanner = new Scanner(System.in);
    HotelierDao ar = new HotelierDaoImpl();
    HotelierService hotelierService = new HotelierServiceImpl(ar);

    public void init() {
        var menu = new Menu("Hotelier Menu", List.of(
                new Menu.Option("Add hotel: ", () -> {
                    var propertyType = new NewHouseDialog().input();
                    System.out.println("Select property type:");
                    hotelierService.selectPropertyId();
                    var typeId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Choose city by ID:");
                    hotelierService.getCities();
                    hotelierService.selectCities();
                    var city = Integer.parseInt(scanner.nextLine());
                    hotelierService.addHotelOrGuestHouse(propertyType, typeId, city);
                    return String.format("House '%s'  added successfully.",
                            propertyType.getName());

                }),
                new Menu.Option("Add rooms to hotel:", () -> {
                    var room = new NewRoomDialog().input();
                    hotelierService.addRoomsToHotel(room);
                    return "Room added";
                })

        ));
        menu.show();
    }
}
