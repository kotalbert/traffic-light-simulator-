package traffic;

public class QueueThread extends Thread {
    private int time;
    private SystemState systemState;
    private final int roads;
    private final int interval;

    QueueThread(int roads, int interval) {
        this.time = 0;
        this.systemState = SystemState.NOT_STARTED;
        this.roads = roads;
        this.interval = interval;
    }

    public void setSystemState(SystemState state) {
        this.systemState = state;
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        systemState = SystemState.MENU;
        while (true) {
            try {
                Thread.sleep(1000);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (systemState == SystemState.SYSTEM) {
                System.out.println("--- Time: " + time + " seconds ---");
                System.out.println("Number of roads: " + roads);
                System.out.println("Interval: " + interval + " seconds");
            }
        }
    }


}
