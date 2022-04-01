package iguigui.javaBean.easyExcelTest;

import java.util.Base64;

public class Demo01 {

    public static void main(String[] args) {

        String s = "YWJjZA";
        System.out.println(new String(Base64.getUrlDecoder().decode(s)));
        System.out.println(new String(Base64.getDecoder().decode(s)));
    }

    public int test(int i) {
        return i * i;
    }


}