package view;

import exception.InvalidEntityDataException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
public class Menu {

    @Getter
    @ToString
    @AllArgsConstructor
    public static class Option {
        private String text;
        private Command command;
    }

    public class ExitCommand implements Command {
        @Override
        public String execute() throws InvalidEntityDataException {
            return String.format("Exiting menu '%s'.", Menu.this.title);
        }
    }

    private String title;
    private List<Option> options = List.of(new Option("Exit", new ExitCommand()));
    private Scanner scanner = new Scanner(System.in);

    public Menu(String title, List<Option> options){
        this.title = title;
        var oldOptions = this.options;
        this.options = new ArrayList<>();
        this.options.addAll(options);
        this.options.addAll(oldOptions);
    }

    public void show(){
        while (true){
            System.out.printf("MENU: %s%n",title);
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%2d. %s%n", i + 1, options.get(i).getText());
            }
            int choice = -1;
            do{
                System.out.printf("Enter your choice (1 - %s):", options.size());
                var choiceStr = scanner.nextLine();
                try{
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException ex){
                    System.out.println("Error. Invalid choice. Please enter a valid number between 1 and " + options.size());
                }
            } while (choice < 1 || choice > options.size());
            String result = null;
            try{
                result = options.get(choice - 1).getCommand().execute();
            } catch (InvalidEntityDataException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println(result);
            if(choice == options.size()){
                break;
            }
        }
    }

}
