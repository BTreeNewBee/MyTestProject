package iguigui.extend;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Test01 {

    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        Integer integer = list.stream().filter(Objects::nonNull).map(Map.Entry::getValue).max(Integer::compareTo).get();
        System.out.println(integer);

    }

}




