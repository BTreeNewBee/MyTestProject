package iguigui.hash;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;

public class MyHashTest {

    //一组具有相同hashCode的最小单元
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("A~");
        strings.add("B_");
        strings.add("C@");
        strings.add("D!");
        for (String string : strings) {
            System.out.println(string + " hashcode = " + string.hashCode());
        }

    }


}


