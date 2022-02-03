package iguigui.github.io.ssltls;


import java.math.BigDecimal;

public class Differ_Hellman {

    public static void main(String[] args) {
        //实例化两个通信者，alice向bob发起连接
        Alice alice = new Alice();
        Bob bob = new Bob();
        //alice向bob传递三个参数：素数p，整数g，以及根据自己内部的隐藏参数a计算出来的A
        //bob接收三个参数，用自己随机选择的隐藏参数b计算返回B
        int B = bob.getB(alice.g, alice.p, alice.getA());
        //Alice接收B，计算得到K
        alice.receiveB(B);
        //到此处完成交换，打印输出双方约定出的k
        alice.printK();
        bob.printK();
    }

}

class Alice {
    //公开参数，此处假定是自己生成的
    // g 是 p的一个原根，p 是素数
    // 原根：首先定义一个素数p的原根，为其各次幂产生从1 到p-1的所有整数根，也就是说，如果g是素数p的一个原根，那么数值
    //                  g mod p, g^2 mod p,…,g^p-1 mod p
    //是各不相同的整数，并且以某种排列方式组成了从1到p-1的所有整数。
    public int g = 5 ,p = 97;

    //私密参数，此处每次实例化时随机选择，0-100
    private int a = (int) (Math.random() * 100);
//    private int a = 36;

    //最终协商出来的密钥
    private int K;
    //计算 g^a mod p
    //因为会溢出，用BigDecimal计算
    public int getA() {
        return BigDecimal.valueOf(g).pow(a).divideAndRemainder(BigDecimal.valueOf(p))[1].intValue();
    }
    //接收B，计算出A
    public void receiveB(int B) {
        K = BigDecimal.valueOf(B).pow(a).divideAndRemainder(BigDecimal.valueOf(p))[1].intValue();
    }
    //打印输出最终协商的密钥K
    public void printK() {
        System.out.println(K);
    }
}


class Bob {
    //私密参数，此处每次实例化时随机选择，0-100
    private int b = (int) (Math.random() * 100);
//    private int b = 58;

    //最终协商出来的密钥
    private int K;
    //接收Alice的三个参数，g p A ，计算返回B，同时把K存起来
    //计算 A^b mod p 得到K
    //计算 g^b mod p 返回B
    public int getB(int g , int p,int A) {
        K = BigDecimal.valueOf(A).pow(b).divideAndRemainder(BigDecimal.valueOf(p))[1].intValue();
        return BigDecimal.valueOf(g).pow(b).divideAndRemainder(BigDecimal.valueOf(p))[1].intValue();
    }
    //打印输出最终协商的密钥K
    public void printK() {
        System.out.println(K);
    }
}