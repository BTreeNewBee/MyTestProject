package iguigui.strings;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Demo01 {

    public static void main(String[] args) {
        double number = 123.40; // 您要格式化的浮点数

        // 创建一个DecimalFormat对象，指定格式
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // 使用format方法进行格式化
        String formattedNumber = decimalFormat.format(number);

        // 输出格式化后的结果
        System.out.println(formattedNumber);
    }

}
