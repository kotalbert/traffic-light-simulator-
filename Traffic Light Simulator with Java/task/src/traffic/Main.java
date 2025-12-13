package traffic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the traffic management system!");
        System.out.print("Input the number of roads: ");
        int numberOfRoads = sc.nextInt();
        System.out.print("Input the interval: ");
        int interval = sc.nextInt();

        boolean running = true;
        while (running) {
            running = handleMenu();
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
                System.out.println("Invalid option. Please try again.");
        }
        return true;
    }

}
