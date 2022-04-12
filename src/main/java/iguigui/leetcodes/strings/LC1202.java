package iguigui.leetcodes.strings;

import java.util.List;

public class LC1202 {
    public static void main(String[] args) {
        List<List<Integer>> lists = List.of(List.of(0, 3), List.of(1, 2));
        System.out.println(new LC1202().smallestStringWithSwaps("bacd",List.of(List.of(0, 3), List.of(1, 2))));
        System.out.println(new LC1202().smallestStringWithSwaps("dcab",List.of(List.of(0, 3), List.of(1, 2), List.of(0, 2))));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        byte[] bytes = s.getBytes();
        boolean smallest = false;
        while (true) {
            smallest = true;
            for (List<Integer> pair : pairs) {
                if (bytes[pair.get(0)] > bytes[pair.get(1)]) {
                    byte b = bytes[pair.get(1)];
                    bytes[pair.get(1)] = bytes[pair.get(0)];
                    bytes[pair.get(0)] = b;
                    smallest = false;
                }
            }
            if (smallest) {
                break;
            }
        }
        return new String(bytes);
    }

}
