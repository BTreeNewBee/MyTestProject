package iguigui.thread;

public class ThreadSafe {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        String s = "";
        Thread thread = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                synchronized (s) {
                    i = i + 1;
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                synchronized (s) {
                    i = i + 1;
                }
            }
        });

        thread.start();
        thread1.start();

        Thread.sleep(1000);
        System.out.println(i);
    }


}
