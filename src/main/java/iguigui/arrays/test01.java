package iguigui.arrays;

import java.util.*;
import java.util.stream.Collectors;

public class test01 {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();


        int[] ints = {1,2,3,4,3,2};
        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        Collections.reverse(collect);
        collect.forEach(System.out::println);
    }

}
