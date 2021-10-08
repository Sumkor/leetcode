package com.sumkor.math._0029_divide;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/8
 */
public class Solution02 {

    /**
     * 先使用位移，再使用位移（递归法）
     * https://leetcode-cn.com/problems/divide-two-integers/solution/wa-pian-29-liang-shu-xiang-chu-java-zhon-3ql4/
     *
     * 全部使用整型，通过将除数和被除数转换为负数来确保在 dividend 为Integer.MIN_VALUE 不会溢出，再在 div 中检查是否发生溢出
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了64.64% 的用户
     */
    public int divide(int dividend, int divisor) { // 被除数 除数
        if (divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE; // 溢出
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            sign = -1;
        int a = dividend > 0 ? -dividend : dividend;
        int b = divisor > 0 ? -divisor : divisor;
        // 都改为负号是因为int 的范围是[2^31, 2^31-1]，如果a是-2^32，转为正数时将会溢出
        // System.out.println(a + " " + b);
        if (a > b) return 0;
        int ans = div(a, b);
        return sign == -1 ? -ans : ans;
    }

    private int div(int a, int b) {
        if (a > b) return 0;
        int count = 1;
        int tb = b;
        while (tb + tb >= a && tb + tb < 0) { // 溢出之后不再小于0
            tb += tb;
            count += count;
            //System.out.println(tb + " " + count + " " + count*b);
        }
        return count + div(a - tb, b);
    }

    @Test
    public void test() {
        int ans = divide(10, 3);
//        int ans = divide(9, 3);
//        int ans = divide(20, 3);
//        int ans = divide(7, -3);
//        int ans = divide(1, -2);
//        int ans = divide(-2147483648, 1);
//        int ans = divide(1, -1);
        System.out.println("ans = " + ans);
    }
}
