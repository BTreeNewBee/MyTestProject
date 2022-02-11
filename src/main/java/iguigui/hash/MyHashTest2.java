package iguigui.hash;

import java.util.ArrayList;

public class MyHashTest2 {

    //具有相同hashCode的单元进行简单重复就可以生成一大堆有相同hashCode的字符串
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A~A~A~A~");
        strings.add("A~A~A~B_");
        strings.add("A~A~A~C@");
        strings.add("A~A~A~D!");
        strings.add("A~A~B_A~");
        strings.add("A~A~B_B_");
        strings.add("A~A~B_C@");
        strings.add("A~A~B_D!");
        for (String string : strings) {
            System.out.println(string + " hashcode = " + string.hashCode());
        }
    }


}


