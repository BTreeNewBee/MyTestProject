package iguigui.strings;

public class Test02 {

    public static void main(String[] args) {
        int[] ints = {1,3,5,4,7};
        System.out.println("max increment " + maxIncr(ints));
        int[] ints1 = {1,3,5,4,7,7,8};
        System.out.println("max increment " + maxIncr(ints1));
        int[] ints2 = {1,3,5,4,7,1,2,3,4,5,6,7,8,9,10};
        System.out.println("max increment " + maxIncr(ints2));

        String s1 = "a";
        String s2 = "abb";
        String s3 = "aaa";
        String s4 = "accccc";
        String s5 = "abbbbbccccc";
        System.out.println(s1 + " -> " + shinkStr(s1));
        System.out.println(s2 + " -> " + shinkStr(s2));
        System.out.println(s3 + " -> " + shinkStr(s3));
        System.out.println(s4 + " -> " + shinkStr(s4));
        System.out.println(s5 + " -> " + shinkStr(s5));

    }

    public static int maxIncr(int[] array) {
        if (array.length < 1) {
            return array.length;
        }
        int result = 1;
        int max = 1;
        int temp = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > temp) {
                max++;
            } else {
                if (max > result) {
                    result = max;
                }
                max = 1;
            }
            temp = array[i];
        }
        return Math.max(result, max);
    }


    public static String shinkStr(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int i = 1;
        char tmp = chars[0];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i1 = 1; i1 < chars.length; i1++) {
            if (chars[i1] == tmp) {
                i ++;
            } else {
                stringBuilder.append(tmp);
                if (i > 1) {
                    stringBuilder.append(i);
                }
                tmp = chars[i1];
                i = 1;
            }
        }
        stringBuilder.append(chars[chars.length - 1]);
        if (i > 1) {
            stringBuilder.append(i);
        }
        return stringBuilder.toString();
    }


}
