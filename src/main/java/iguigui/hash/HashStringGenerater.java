package iguigui.hash;

import java.io.*;

public class HashStringGenerater {


    //简单生成一大堆具有相同hashCode的字符串
    //输出到文件去
    public static void main(String[] args) {
        String[] strings = {"A~", "B_", "C@", "D!"};
        int round = 8;
        int pow = (int) Math.pow(4, round);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("hash.json")));
            writer.write("{");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < pow - 1; i++) {
                for (int j = round - 1; j >= 0; j--) {
                    stringBuilder.append(strings[(i / ((int) Math.pow(4, j))) % 4]);
                }
                writer.write("\"");
                writer.write(stringBuilder.toString());
                writer.write("\":0,");
                stringBuilder.delete(0,stringBuilder.length());
            }
            for (int j = round - 1; j >= 0; j--) {
                stringBuilder.append(strings[(pow / ((int) Math.pow(4, j))) % 4]);
            }
            writer.write("\"");
            writer.write(stringBuilder.toString());
            writer.write("\":0}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
