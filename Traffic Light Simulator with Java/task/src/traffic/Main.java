package traffic;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the traffic management system!");
        getNumberOfRoads(sc);
        getInterval(sc);
        clearConsole();

        boolean running = true;
        while (running) {
            running = handleMenu();
        }
    }

    /**
     * Interval must be > 0
     *
     * @param sc
     */
    private static void getInterval(Scanner sc) {
        System.out.print("Input the interval: ");
        while (true) {
            try {
                int interval = Integer.parseInt(sc.nextLine());
                if (interval > 0) {
                    break;
                } else {
                    System.out.print("Incorrect input. Please try again:");
                    sc.next();
                }
            } catch (Exception e) {
                System.out.print("Incorrect input. Please try again:");
            }
        }
    }

    /**
     * Number of roads must be > 0
     *
     * @param sc
     */
    private static void getNumberOfRoads(Scanner sc) {
        System.out.print("Input the number of roads: ");
            while (true) {
                try {
                    int numberOfRoads = Integer.parseInt(sc.nextLine());
                if (numberOfRoads > 0) {
                    break;
                } else {
                    System.out.print("Incorrect input. Please try again:");
                    sc.next();
                }
                } catch (Exception e) {
                    System.out.print("Incorrect input. Please try again:");
                }
            }
    }

    private static boolean handleMenu() {
        System.out.println("""
                Menu:
                1. Add road
                2. Delete road
                3. Open system
                0. Quit""");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Road added");
                break;
            case 2:
                System.out.println("Road deleted");
                break;
            case 3:
                System.out.println("System opened");
                break;
            case 0:
                System.out.println("Bye!");
                return false;
            default:
                System.out.println("Incorrect option. Please try again.");
        }
        sc.nextLine(); // Consume newline
        sc.nextLine();
        clearConsole();
        return true;
    }

    private static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ignored) {
        }
    }

}
