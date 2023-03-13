package iguigui.closures;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Test {

    public static void main(String[] args) {
        Supplier<Integer> test0 = test();
        Supplier<Integer> test1 = test();
        System.out.println(test0);
        System.out.println(test0.get());
        System.out.println(test0.get());


        System.out.println(test1.get());
        System.out.println(test1.get());
        System.out.println(test1.get());
        System.out.println(test1.get());
    }

    public static Supplier<Integer> test() {
        final int[] i = {1};
        return () -> i[0]++;
    }

}
