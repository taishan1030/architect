package com.wls.alg.class01;

/**
 * @Author:wangpeng
 * @Date: 2021/12/11
 * @Description:　给一个整数，返回不小于该数的一个整数，此数是2的n次方
 * @version:1.0
 */
public class Code03_Near2Power {

    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    public static int tableSizeFor(int n) {

        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;

    }

    public static void main(String[] args) {
        int cap = 120;
        System.out.println(tableSizeFor(cap));
    }
}
