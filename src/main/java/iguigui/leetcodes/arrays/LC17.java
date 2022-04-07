package iguigui.leetcodes.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC17 {

    public static void main(String[] args) {
        LC17 lc17 = new LC17();
        List<String> strings = lc17.letterCombinations("797");
        System.out.println(strings);
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return Collections.EMPTY_LIST;
        }
        byte[] bytes = digits.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] - 0x30);
        }
        //计算总共有多少种组合，例如23就是3*3种组合
        int count = 1;
        for (byte aByte : bytes) {
            //7和9居然有四个字母，其他的都是三个，处理一下
            if (aByte != 9 && aByte != 7) {
                count *= 3;
                continue;
            }
            count *= 4;
        }
        //得到总数，提前初始化数组，降低扩容成本
        ArrayList<String> result = new ArrayList<>(count);
        //开始递归
        addChar(result,new byte[bytes.length],0,bytes);
        return result;
    }

    /**
     * @param result 最终的结果
     * @param tmp 临时byte数组，用于存储每次生成的string
     * @param index 当前递归到第几层
     * @param buttons 输入的按键列表
     */
    public void addChar(List<String> result,byte[] tmp,int index,byte[] buttons) {
        //在这一层需要loop的次数，其实就是这个按键有几个字母
        int loop = 3;
        //ascii 码，字母 a，用于下文计算这一次应该拼什么字母
        int base = 0x61;
        //7和9居然有四个字母，其他的都是三个，处理一下
        if (buttons[index] == 9 || buttons[index] == 7) {
            loop = 4;
        }
        //由于7有四个字母导致base要 +1
        if (buttons[index] > 7) {
            base ++;
        }
        //一次loop就是拼这个按键的一个字母
        for (int i = 0; i < loop; i++) {
            //计算这次loop的字母是什么，然后把他塞到此次递归的深度index里面
            //按键2 第0次 = a，计算过程：0x61 + 0 + (2 - 2 ) * 3 = 0x61 = 'a'
            //按键3 第2次 = f，计算过程：0x61 + 2 + (3 - 2 ) * 3 = 0x6f = 'f'
            tmp[index] = (byte) ((buttons[index] - 2) * 3 + base + i);
            //当递归到最底层，index == buttons.length - 1，为递归结束条件，生成string填入result，continue
            if (index == buttons.length - 1) {
                result.add(new String(tmp));
                continue;
            }
            //没到最底层，继续向下递归
            addChar(result,tmp,index + 1,buttons);
        }
    }


}
