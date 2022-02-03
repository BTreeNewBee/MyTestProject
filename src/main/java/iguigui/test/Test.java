package iguigui.test;

public class Test {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{1,2,3,4};
        long byteslong = bytes[0] << 8 * 3
                | bytes[1] << 8 * 2
                | bytes[2] << 8
                | bytes[3]  ;
        System.out.println(byteslong);
    }

}
