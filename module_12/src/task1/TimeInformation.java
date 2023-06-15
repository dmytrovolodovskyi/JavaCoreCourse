package task1;

public class TimeInformation {
    private static int seconds = 1;

    public static void main(String[] args) {

        Thread timeThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(seconds++);
                }
        });

        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("5 seconds have passed");
            }
        });

        timeThread.start();
        messageThread.start();
    }
}

