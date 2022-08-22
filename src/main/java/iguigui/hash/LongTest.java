package iguigui.hash;

import java.util.HashSet;
import java.util.Set;

public class LongTest {
    static long[] ids = {1, 2, 3, 4, 5};
    static Set<Long> idsSet = new HashSet();

    static {
        for (long id : ids) {
            idsSet.add(id);
        }
    }

    public static void main(String[] args) {

        int i = 10;
        // <-
        if (0 <- i) {
            System.out.println(i);
        }
        // <==
        if (0 == i) {
            System.out.println(i);
        }
        // --> 趋向于运算符，i会以1为步长抵达目标值
        while (0 <-- i) {
            System.out.println(i);
        }
    }

}
