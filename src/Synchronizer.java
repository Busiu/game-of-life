public class Synchronizer {

    private int FPS;
    private long startTime;

    public Synchronizer(int FPS) {
        this.FPS = FPS;
    }

    public void startMeasureTime() {
        startTime = System.currentTimeMillis();
    }

    public void synchronize() {
        long endTime = System.currentTimeMillis();
        long sleepTime = 1000 / FPS - (endTime - startTime);
        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
