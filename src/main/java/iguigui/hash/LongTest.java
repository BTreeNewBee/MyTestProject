package iguigui.hash;

import java.util.HashSet;
import java.util.Set;

public class LongTest {
    static long[] ids = {1,2,3,4,5};
    static Set<Long> idsSet = new HashSet();
    static {
        for (long id : ids) {
            idsSet.add(id);
        }
    }
    public static void main(String[] args) {
        long id = 5;
        if (idsSet.contains(id)) {
            System.out.println("Do what you want ");
        }
    }

}
