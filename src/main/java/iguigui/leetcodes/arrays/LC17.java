package iguigui.leetcodes.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC17 {

    public static void main(String[] args) {
        LC17 lc17 = new LC17();
        List<String> strings = lc17.letterCombinations("797");
        System.out.println(strings);
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
            if (aByte != 9 && aByte != 7) {
                count *= 3;
                continue;
            }
            count *= 4;
        }
        ArrayList<String> result = new ArrayList<>(count);
        addChar(result,new byte[bytes.length],0,bytes);
        return result;
    }

    public void addChar(List<String> result,byte[] tmp,int index,byte[] buttons) {
        int loop = 3;
        int base = 0x61;
        if (buttons[index] == 9 || buttons[index] == 7) {
            loop = 4;
        }
        if (buttons[index] > 7) {
            base ++;
        }
        for (int i = 0; i < loop; i++) {
            tmp[index] = (byte) ((buttons[index] - 2) * 3 + base + i);
            if (index == buttons.length - 1) {
                result.add(new String(tmp));
                continue;
            }
            addChar(result,tmp,index + 1,buttons);
        }
    }


}
