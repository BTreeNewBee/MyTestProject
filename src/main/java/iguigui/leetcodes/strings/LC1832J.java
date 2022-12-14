package iguigui.leetcodes.strings;

public class LC1832J {

    public static void main(String[] args) {
        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        System.out.println(checkIfPangram("leetcode"));
    }
    public static boolean checkIfPangram(String sentence) {
        byte[] bytes = sentence.getBytes();
        int mask = 0;
        for (byte aByte : bytes) {
            mask |= 1<< aByte - 0x61;
        }
        return mask == 0x3ffffff;
    }
}
