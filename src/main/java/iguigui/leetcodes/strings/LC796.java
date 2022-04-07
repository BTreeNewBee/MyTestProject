package iguigui.leetcodes.strings;

import iguigui.leetcodes.arrays.LCT;

public class LC796 {
    public static void main(String[] args) {
        System.out.println(new LC796().rotateString("abcde","bcdea"));
        System.out.println(new LC796().rotateString("abcde","abced"));
    }

    public boolean rotateString(String s, String goal) {
        if (s == goal) {
            return true;
        }else if (s == null || goal == null || s.length() != goal.length()) {
            return false;
        }
        goal += goal;
        return goal.contains(s);
    }

}
