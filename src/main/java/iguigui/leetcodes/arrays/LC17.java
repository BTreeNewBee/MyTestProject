package iguigui.leetcodes.arrays;

import java.util.Collections;
import java.util.List;

public class LC17 {

    public static void main(String[] args) {
        LC17 lc17 = new LC17();
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.EMPTY_LIST;
        }
        byte[] bytes = digits.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - 0x30);
        }
        int count = 1;
        for (byte aByte : bytes) {
            if (aByte != 9) {
                count *= 3;
            } else {
                count *= 4;
            }
        }
        for (byte aByte : bytes) {

        }

        return null;
    }


}
