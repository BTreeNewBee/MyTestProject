package iguigui.npl;

import java.util.HashMap;
import java.util.Map;

public class Demo01 {

    public static void main(String[] args) {
        Map<Integer, String> maps = new HashMap<>(200);
        for (int i = 0; i < 100; i++) {
            maps.put(i, String.valueOf(i));
        }
        maps.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        String[] split = ",,,,".split(",");

        System.out.println(split.length);
    }

}
