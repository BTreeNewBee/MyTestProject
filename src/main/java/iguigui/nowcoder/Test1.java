package iguigui.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Test1 {

    public static void main(String[] args) {
//        int[] array = new int[10];
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            array[i] = random.nextInt(1000);
//        }
//        int[] array = {1,2,3,4,5,6,7,8,9,10};
        int[] array = {1,1,1,1,1,1,1,3,2,2};
        //排序
        Arrays.sort(array);
        ArrayList<Integer> integers1 = new ArrayList<>();
        ArrayList<Integer> integers2 = new ArrayList<>();
        int ints1Sum = 0;
        int ints2Sum = 0;
        for (int i : array) {
            if (ints1Sum < ints2Sum) {
                integers2.add(i);
                ints2Sum += i;
            } else {
                integers1.add(i);
                ints1Sum += i;
            }
        }
        System.out.println(integers1);
        System.out.println(integers2);
        System.out.println(ints1Sum);
        System.out.println(ints2Sum);
        System.out.println(ints1Sum - ints2Sum);
    }

}
