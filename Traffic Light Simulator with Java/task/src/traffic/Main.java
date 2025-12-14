package traffic;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QueueThread qt = gatherProgramParameters();
        qt.start();
        boolean running = true;
        while (running) {
            running = handleMenu(qt);
        }
    }

    /**
     * Gather initial program parameters from user
     *
     * @return QueueThread with user parameters
     */
    private static QueueThread gatherProgramParameters() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the traffic management system!");
        int roads = getNumberOfRoads(sc);
        int interval = getInterval(sc);

        clearConsole();
        return new QueueThread(roads, interval);
    }

    /**
     * Get user input either for interval or number of roads
     * <p>
     * Assume input must be integer > 0.
     *
     *
     */
    private static int getInterval(Scanner sc) {
        System.out.print("Input the interval: ");
        return getUserInput(sc);
    }

    private static int getUserInput(Scanner sc) {
        int userInput;
        while (true) {
            try {
                userInput = Integer.parseInt(sc.nextLine());
                if (userInput > 0) {
                    break;
                } else {
                    System.out.print("Incorrect input. Please try again:");
                }
            } catch (Exception e) {
                System.out.print("Incorrect input. Please try again:");
            }
        }
        return userInput;
    }

    /**
     * Number of roads must be > 0
     *
     */
    private static int getNumberOfRoads(Scanner sc) {
        System.out.print("Input the number of roads: ");
        return getUserInput(sc);
    }

    private static boolean handleMenu(QueueThread qt) {
        qt.setSystemState(SystemState.MENU);
        System.out.println("""
                Menu:
                1. Add road
                2. Delete road
                3. Open system
                0. Quit""");
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            choice = -1;
        }
        switch (choice) {
            case 1:
                System.out.println("Road added");
                break;
            case 2:
                System.out.println("Road deleted");
                break;
            case 3:
                System.out.println("System opened");
                qt.setSystemState(SystemState.SYSTEM);
                break;
            case 0:
                System.out.println("Bye!");
                qt.stopThread();
                try {
                    qt.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return false;
            default:
                System.out.println("Incorrect option. Please try again.");
        }
        sc.nextLine(); // Consume newline
        clearConsole();
        return true;
    }

    static void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows") ? new ProcessBuilder("cmd", "/c", "cls") : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ignored) {
        }
    }

}
