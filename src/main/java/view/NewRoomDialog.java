package view;


import entity.enums.TypeOfRoom;
import entity.hotelAndHouse.Room;
import java.util.Scanner;

public class NewRoomDialog implements EntityDialog<Room> {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public Room input() {
        var room = new Room();
        while (room.getTypeOfRoom() == null){
            //DOUBLE,TRIPLE,APARTMENT,LUX
            System.out.println("Choose type of room:");
            var answer = scanner.nextLine().toUpperCase();
            if (answer.equals("DOUBLE") || answer.equals("TRIPLE") || answer.equals("APARTMENT") || answer.equals("LUX")) {
                room.setTypeOfRoom(TypeOfRoom.valueOf(answer));
            } else {
                System.out.println("Room type is invalid.");
            }
        }

        while (room.getPricePerDay() == 0){
            System.out.println("Set room price per day:");
            var answer = Double.parseDouble(scanner.nextLine());
            if(answer < 0){
                System.out.println("Price should be not negative number");
            } else {
                room.setPricePerDay(answer);
            }
        }

        return room;
    }
}
