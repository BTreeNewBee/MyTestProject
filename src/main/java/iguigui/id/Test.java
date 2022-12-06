package iguigui.id;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 1000; i++) {
            System.out.println(test.getId());
        }

    }

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getId() {
        int i = atomicInteger.incrementAndGet();
        while (!pass(i)) {
            i = atomicInteger.incrementAndGet();
        }
        return i;
    }

    private boolean pass(int i) {
        String s = String.valueOf(i);
        int n = 3;
        n = Math.min(n, s.length());
        if (n == 1) {
            return true;
        }
        byte[] bytes = s.getBytes();
        for (int i1 = 0; i1 < bytes.length - 2; i1++) {
            if (!filter(bytes, i1)) {
                return false;
            }
        }
        return true;
    }

    private boolean filter(byte[] b, int index) {
        int mask = 0;
        for (int j = 0; j < 3; j++) {
            if ((mask & 1 << b[index + j]) != 0) {
                return false;
            }
            mask |= 1 << b[index + j];
        }
        return true;
    }

}
