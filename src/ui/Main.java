package ui;

import java.util.Scanner;

//Консольный ввод/вывод
public class Main {
    private static ConsoleUI consoleUI = new ConsoleUI();

    public static void main(String[] args) {
        System.out.println("Program is starting");

        while (true) {
            System.out.println(System.lineSeparator() + "Please, input a command");

            String commandString = new Scanner(System.in).nextLine();

            String[] commandLine = new String[3];
            String[] splittedCommandLine = commandString.split(" ");

            System.arraycopy(splittedCommandLine, 0, commandLine, 0, 3);

            String command = commandLine[0];
            String firstArg = commandLine[1];
            String secondArg = commandLine[2];


            if (command.equals("/exit")) {
                System.out.println(consoleUI.saveAllData());
                System.out.println("Program is shutting down");
                break;
            }

            switch (command) {
                case "/help" -> System.out.println("""
                        /exit - shut down the program
                        /help - show command list
                        /names - show names of all dictionaries
                        /switch <dict's name> - start working with specified dictionary
                        /show - show specified dictionary
                        /delete <key> - delete "key - value" pair from specified dictionary
                        /find <key> - find value from specified dictionary
                        /add  <key> <value> - add "key - value" pair in specified dictionary
                        /save - save current dictionary""");
                case "/names" -> System.out.println(consoleUI.getNames());
                case "/switch" -> System.out.println(consoleUI.switchSpecifiedDict(firstArg));
                case "/show" -> System.out.println(consoleUI.getCurrentDict());
                case "/delete" -> System.out.println(consoleUI.deleteFromCurrentDict(firstArg));
                case "/find" -> System.out.println(consoleUI.findInCurrentDict(firstArg));
                case "/add" -> System.out.println(consoleUI.addInCurrentDict(firstArg, secondArg));
                case "/save" -> System.out.println(consoleUI.saveCurrentDict());
                default -> System.out.println("Unknown command");
            }
        }
    }
}