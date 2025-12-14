package traffic;

import static traffic.Main.clearConsole;

public class QueueThread extends Thread {
    private int time = 0;
    private volatile SystemState systemState = SystemState.NOT_STARTED;
    private final int roads;
    private final int interval;
    private volatile boolean running = true;

    QueueThread(int roads, int interval) {
        this.roads = roads;
        this.interval = interval;
    }

    public void setSystemState(SystemState state) {
        this.systemState = state;
    }

    public void stopThread() {
        this.running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        systemState = SystemState.MENU;
        while (running) {
            try {
                Thread.sleep(1000);
                time++;

            } catch (InterruptedException e) {
                break; // exit the loop if interrupted
            }
            if (systemState == SystemState.SYSTEM) {
                clearConsole();
                System.out.println("--- Time: " + time + " seconds ---");
                System.out.println("! Number of roads: " + roads + " !");
                System.out.println("! Interval: " + interval + " !");
                System.out.println("Press \"Enter\" to open menu !");
            }
        }
    }


}
