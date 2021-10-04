package com.sumkor.math._0050_pow;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/3
 */
public class Solution {

    /**
     * stackOverFlow
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return recur(x, x, n);
        }
        return 1 / recur(x, x, -n);
    }

    private double recur(double x, double sum, int n) {
        if (n == 1) {
            return sum;
        }
        sum = sum * x;
        return recur(x, sum, n - 1);
    }

    /**
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     *
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     *
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     *
     * 0.00001
     * 2147483647
     */
    @Test
    public void test() {
//        double pow = myPow(2d, 10);
//        double pow = myPow(2.1d, 3);
//        double pow = myPow(2d, -2);
//        double pow = myPow(0.00001d, 2147483647);
        double pow = myPow(0.00001d, 2);
        System.out.println("pow = " + pow);

    }
}
