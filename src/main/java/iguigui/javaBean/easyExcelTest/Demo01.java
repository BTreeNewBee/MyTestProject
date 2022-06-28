package iguigui.javaBean.easyExcelTest;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Demo01 {

    public static void main(String[] args) {
        String s = "YWJjZA";
        System.out.println(new String(Base64.getUrlDecoder().decode(s)));
        System.out.println(new String(Base64.getDecoder().decode(s)));

        //Javaの烂语法
        //1.5
        Map<String,String> map1 = new HashMap<String,String>();
        //1.7
        Map map2 = new HashMap<String,String>();
        Map<String,String> map3 = new HashMap();
        //11
        Map<String,String> map4 = new HashMap<String,String>();

    }

    public int test(int i) {
        return i * i;
    }


    public int add1(int i) {
        //局部变量类型推导
        int a = 10;
        return a * i;
    }

}