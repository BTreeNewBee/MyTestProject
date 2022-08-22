package iguigui.volatiles;

public class Test {

    static int i = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (i % 2 == 1) {
                    System.out.println(i);
                    i++;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (i % 2 == 0) {
                    System.out.println(i);
                    i++;
                }
            }
        }).start();

    }

}
