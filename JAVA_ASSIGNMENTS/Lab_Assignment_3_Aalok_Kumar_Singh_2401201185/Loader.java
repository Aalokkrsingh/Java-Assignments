public class Loader implements Runnable {
    private String message;
    private int durationMs;

    public Loader(String message, int durationMs) {
        this.message = message;
        this.durationMs = durationMs;
    }

    @Override
    public void run() {
        try {
            System.out.print(message);
            int steps = durationMs / 250;
            for (int i = 0; i < steps; i++) {
                Thread.sleep(250);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("\nLoading interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}
