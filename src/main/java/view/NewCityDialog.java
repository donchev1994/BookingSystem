package view;

import entity.city.City;

import java.util.Scanner;

public class NewCityDialog  implements EntityDialog<City> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public City input() {
        var city = new City();
        while (city.getName() == null){
            System.out.println("City name:");
            var answer = scanner.nextLine();
            if (answer.length() < 2 || answer.length() > 15) {
                System.out.println("City Name length should be between 2 and 15 characters.");
            } else {
                city.setName(answer);
            }
        }

        while (city.getDescription() == null){
            System.out.println("City description");
            var answer = scanner.nextLine();
            if(answer.length() < 20 || answer.length() > 350){
                System.out.println("Description length should be between 20 and 350 characters");
            } else {
                city.setDescription(answer);
            }
        }

        return city;
    }
}
