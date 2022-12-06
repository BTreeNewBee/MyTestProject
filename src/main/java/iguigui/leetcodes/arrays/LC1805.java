package iguigui.leetcodes.arrays;

import java.util.Arrays;
import java.util.Objects;

public class LC1805 {
    public static void main(String[] args) {
        System.out.println(new LC1805().test("abc123abc00123abcde1"));
    }

    public long test(String s) {
        return Arrays.stream(s.replaceAll("[a-z]", " ").split(" "))
                .filter(e -> !Objects.equals(e, ""))
                .mapToInt(Integer::parseInt)
                .distinct()
                .count();
    }

}
