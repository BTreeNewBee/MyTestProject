package iguigui.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapsDemo {

    public static void main(String[] args) {

        String collect = new TreeMap<>(Map.of("ab", 1, "cd", 2, "ef", 3))
                .entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));

        System.out.println(collect);

    }

}
