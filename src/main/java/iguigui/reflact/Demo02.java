package iguigui.reflact;

import java.lang.reflect.Method;

public class Demo02 {
    public static void main(String[] args) {

        for (Method method : UvUcDemo.class.getMethods()) {
            System.out.println(method);
        }

    }
}
