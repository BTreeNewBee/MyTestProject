package iguigui.github.io.ssltls;

import java.math.BigDecimal;

public class RSADemo {

    public static void main(String[] args) {
        //选择两个素数p和q
        int p = 47, q = 71;
        //计算 n = p * q 和 z = (p - 1)*(q - 1)
        // 则 n = 3337，z = 3220
        int n = p * q;
        int z = (p - 1)  * (q - 1);
        //找一个小于n的数值e，与z无公因数，这里取79
        //求一个数d，使得 ed  mod z = 1
        int e = 79,d = 1019;
        //则我们得出了我们的公钥(3337,79)，私钥(3337,1019)
        //RSA将明文进行分组进行运算，分组的信息量应该小于logn，应该选择一个比n小的最大二次幂
        //在这里n = 3337，比他小的最大整次幂是2048 = 2 ^ 11 ，简单起见我们按照每byte进行分组 （8bit）

        String s = "I love Java!";
        byte[] bytes = s.getBytes();

        int[] ints = new int[bytes.length];

        //循环计算密文，每次1byte
        for (int i = 0; i < bytes.length; i++) {
            //计算 c = m ^ e mod n 得到密文
            BigDecimal c = BigDecimal.valueOf(bytes[i]).pow(e).divideAndRemainder(BigDecimal.valueOf(n))[1];
            //存储起来
            ints[i] = c.intValue();
        }

        //循环解码
        for (int i = 0; i < ints.length; i++) {
            //计算 m = c ^ d mod n 得到明文
            BigDecimal m = BigDecimal.valueOf(ints[i]).pow(d).divideAndRemainder(BigDecimal.valueOf(n))[1];
            System.out.print((char) m.intValue());
        }


    }

}
