package iguigui.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static class Task implements Runnable{
        CountDownLatch countDownLatch;
        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000) + 100);//模拟干点啥
                System.out.println("Sub task finish!");
            } catch (InterruptedException e) {}
            countDownLatch.countDown(); //计数器-1
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int taskCount = 10;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++) {
            executorService.execute(new Task(countDownLatch));
        }
        countDownLatch.await(); //等待直到计数器 <= 0
        System.out.println("All task execute succeed!");
        executorService.shutdown();
    }

}
