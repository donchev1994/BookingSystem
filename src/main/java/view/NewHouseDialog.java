package view;

import entity.city.City;
import entity.enums.PropertyType;
import entity.hotelAndHouse.HousesProperties;

import java.util.Scanner;

public class NewHouseDialog implements EntityDialog<HousesProperties> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public HousesProperties input() {
        var house = new HousesProperties();
        while (house.getName() == null){
            System.out.println("House name:");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 30) {
                System.out.println("Property Name length should be between 2 and 30 characters.");
            } else {
                house.setName(answer);
            }
        }

        while (house.getDescription() == null){
            System.out.println("House description:");
            var answer = scanner.nextLine();
            if(answer.length() < 20 || answer.length() > 350){
                System.out.println("Description length should be between 20 and 350 characters.");
            } else {
                house.setDescription(answer);
            }
        }

        while (house.getStars() == 0){
            System.out.println("House stars:");
            var answer = Integer.parseInt(scanner.nextLine());
            if(answer < 1 || answer > 5){
                System.out.println("House stars should be between 1 and 5.");
            } else {
                house.setStars(answer);
            }
        }

        while (house.getAddress() == null){
            System.out.println("House address:");
            var answer = scanner.nextLine();
            if(answer.length() < 5 || answer.length() > 25){
                System.out.println("Address length should be between 5 and 25 characters");
            } else {
                house.setAddress(answer);
            }
        }


        return house;
    }
}
