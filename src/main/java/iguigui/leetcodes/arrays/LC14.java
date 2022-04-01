package iguigui.leetcodes.arrays;

public class LC14 {

    public static void main(String[] args) {
        LC14 lc14 = new LC14();
        String[] strings = {"flower","flow","flight"};
        System.out.println( lc14.longestCommonPrefix(strings));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        byte[][] bytes = new byte[strs.length][];
        byte[] str0Bytes = strs[0].getBytes();
        int minLength = str0Bytes.length;
        byte[] bytes1;
        for (int i = 0; i < strs.length; i++) {
            bytes1 = strs[i].getBytes();
            bytes[i] = bytes1;
            minLength = minLength < bytes1.length ? minLength : bytes1.length;
        }
        if (minLength == 0) {
            return "";
        }
        byte module ;
        int i = 0;
        outer:
        for (; i < minLength; i++) {
            module = str0Bytes[i];
            for (int j = 1; j < strs.length; j++) {
                if (bytes[j][i] != module) {
                    break outer;
                }
            }
        }
        byte[] bytes2 = new byte[i];
        System.arraycopy(str0Bytes,0,bytes2,0,i);
        return new String(bytes2);
    }

}
