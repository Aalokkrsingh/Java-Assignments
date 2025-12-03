package util;

public class Loader implements Runnable {
    private String message;
    private long durationMillis;

    public Loader(String message, long durationMillis) {
        this.message = message;
        this.durationMillis = durationMillis;
    }

    @Override
    public void run() {
        long end = System.currentTimeMillis() + durationMillis;
        try {
            while (System.currentTimeMillis() < end) {
                System.out.print("\r" + message);
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    Thread.sleep(300);
                }
                System.out.print("\r" + message + "   ");
            }
        } catch (InterruptedException e) {
            // thread interrupted when operation completed
        }
        System.out.println();
    }
}
