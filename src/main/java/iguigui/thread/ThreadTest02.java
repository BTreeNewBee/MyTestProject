package iguigui.thread;

import cn.hutool.core.thread.ThreadUtil;

public class ThreadTest02 {

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Thread 1111");
                ThreadUtil.sleep(1000);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100 ; i++) {
                System.out.println("Thread 2222");
                ThreadUtil.sleep(1000);
            }
        }).start();
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
    }
}
