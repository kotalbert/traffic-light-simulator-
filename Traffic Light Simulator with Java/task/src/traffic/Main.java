package traffic;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the traffic management system!");
        System.out.print("Input the number of roads: ");
        int numberOfRoads = scanner.nextInt();
        System.out.print("Input the interval: ");
        int interval = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline
        
        if (numberOfRoads <= 0 || interval <= 0) {
            System.out.println("Error: Number of roads and interval must be positive values.");
            scanner.close();
            return;
        }
        
        System.out.println();
        System.out.println("Starting traffic light simulation with " + numberOfRoads + " roads and " + interval + " second intervals.");
        
        TrafficLight[] trafficLights = new TrafficLight[numberOfRoads];
        for (int i = 0; i < numberOfRoads; i++) {
            trafficLights[i] = new TrafficLight("Road " + (i + 1), i == 0);
        }
        
        // Initialize the first light's timer
        if (numberOfRoads > 0) {
            trafficLights[0].resetTime(interval);
        }
        
        int seconds = 0;
        boolean running = true;
        
        while (running) {
            System.out.println("\nTime: " + seconds + "s");
            for (TrafficLight light : trafficLights) {
                System.out.println(light);
            }
            
            System.out.print("\nPress Enter to continue or type 'quit' to exit: ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("quit")) {
                running = false;
            } else {
                seconds += interval;
                updateTrafficLights(trafficLights, interval);
            }
        }
        
        System.out.println("Traffic light simulation ended.");
        scanner.close();
    }
    
    private static void updateTrafficLights(TrafficLight[] lights, int interval) {
        int greenLightIndex = -1;
        
        // Find the currently green light and update its time
        for (int i = 0; i < lights.length; i++) {
            if (lights[i].isGreen()) {
                greenLightIndex = i;
                lights[i].updateTime(interval);
                break;
            }
        }
        
        // Check if we need to switch lights
        if (greenLightIndex != -1 && lights[greenLightIndex].getTimeRemaining() <= 0) {
            lights[greenLightIndex].setState(TrafficLightState.RED);
            
            int nextLightIndex = (greenLightIndex + 1) % lights.length;
            lights[nextLightIndex].setState(TrafficLightState.GREEN);
            lights[nextLightIndex].resetTime(interval);
        }
    }
}

class TrafficLight {
    private String name;
    private TrafficLightState state;
    private int timeRemaining;
    
    public TrafficLight(String name, boolean isGreen) {
        this.name = name;
        this.state = isGreen ? TrafficLightState.GREEN : TrafficLightState.RED;
        this.timeRemaining = 0;
    }
    
    public void setState(TrafficLightState state) {
        this.state = state;
    }
    
    public void resetTime(int time) {
        this.timeRemaining = time;
    }
    
    public void updateTime(int interval) {
        if (state == TrafficLightState.GREEN) {
            timeRemaining -= interval;
        }
    }
    
    public boolean isGreen() {
        return state == TrafficLightState.GREEN;
    }
    
    public int getTimeRemaining() {
        return timeRemaining;
    }
    
    @Override
    public String toString() {
        return name + ": " + state + (state == TrafficLightState.GREEN ? " (" + timeRemaining + "s remaining)" : "");
    }
}

enum TrafficLightState {
    RED, GREEN
}
