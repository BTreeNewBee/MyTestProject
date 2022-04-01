package iguigui.leetcodes.arrays;

import java.util.HashMap;
import java.util.Map;

public class LC13 {

    public static void main(String[] args) {
        LC13 lc13 = new LC13();
        System.out.println(lc13.romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        short[] ints = new short[92];
        ints['I' - 0x40] = 1;
        ints['V' - 0x40] = 5;
        ints['X' - 0x40] = 10;
        ints['L' - 0x40] = 50;
        ints['C' - 0x40] = 100;
        ints['D' - 0x40] = 500;
        ints['M' - 0x40] = 1000;
        byte[] chars = s.getBytes();
        int length = chars.length;
        int pre = ints[chars[0]- 0x40];
        int count = pre;
        for (int i = 1; i < length; i++) {
            int next = ints[chars[i] - 0x40];
            count += next;
            if (next > pre) {
                count -= pre << 1;
            }
            pre = next;
        }
        return count;
    }
}
