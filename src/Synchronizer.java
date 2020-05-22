public class Synchronizer {

    private final static int FPS = 30;
    private long startTime;

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
