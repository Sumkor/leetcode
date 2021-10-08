package com.sumkor.math._0029_divide;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/8
 */
public class Solution {

    /**
     * 先使用位移，再使用位移（迭代法）
     * 1. 对除数进行位移，翻倍得到被除数的大致范围
     * 2. 被除数减去翻倍后的数，继续执行第一步
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了98.67% 的用户
     */
    public int divide(int dividend, int divisor) {
        // 正负
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        long dividendL = dividend;
        dividendL = dividendL > 0 ? dividendL : -dividendL;
        long divisorL = divisor;
        divisorL = divisorL > 0 ? divisorL : -divisorL;
        // 位移法
        long ans = 0;
        while (dividendL >= divisorL) {
            long count = -1;
            long target = divisorL;
            while (dividendL >= target) {
                target = target << 1;
                count++;
            }
            // 注意！走到这里 count 不会等于 -1
            ans += 1L << count; // 记录当前 divisor 翻了多少倍
            dividendL = dividendL - (divisorL << count); // 减掉已翻倍部分
        }
        // 溢出
        ans = sign == -1 ? -ans : ans;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    /**
     * 先使用位移，再使用减法
     * 1. 对除数进行位移，翻倍得到被除数的大致范围
     * 2. 被除数减去翻倍后的数，再利用减法得到精确的商
     *
     * 这里的问题在于，第一步完成位移之后，第二步做循环减法，很耗时！
     *
     * 执行用时：616 ms, 在所有 Java 提交中击败了8.07% 的用户
     * 内存消耗：35.6 MB, 在所有 Java 提交中击败了33.76% 的用户
     */
    public int divide2(int dividend, int divisor) {
        // 特例
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == 0) {
            return 0;
        }
        long dividendL = dividend;
        long divisorL = divisor;
        // 正负
        boolean is1stNegative = false;
        if (dividendL < 0) {
            is1stNegative = true;
            dividendL = -dividendL;
        }
        boolean is2ndNegative = false;
        if (divisorL < 0) {
            is2ndNegative = true;
            divisorL = -divisorL;
        }
        if (dividendL < divisorL) {
            return 0;
        }
        long ans = 0;
        // 位移
        long count = -1; // 记录 divisor 位移次数
        long target = divisorL;
        while (dividendL >= target) {
            target = target << 1;
            count++;
        }
        if (count == 0) {
            ans = 1;
        } else {
            ans = 2L << (count - 1);
        }
        // 减法
        dividendL = dividendL - (divisorL << count); // 先减掉已经位移过的部分
        while (divisorL <= dividendL) { // 耗时！
            ans++;
            dividendL -= divisorL;
        }
        // 正负
        if ((is1stNegative && !is2ndNegative)
                || (!is1stNegative && is2ndNegative)) {
            ans = -ans;
        }
        // 溢出
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    /**
     * 将两数相除，要求不使用乘法、除法和 mod 运算符
     *
     * 只用减法，超出时间限制
     */
    public int divide0(int dividend, int divisor) {
        // 特例
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == 0) {
            return 0;
        }
        long a = dividend;
        long divisorL = divisor;
        // 正负
        boolean is1stNegative = false;
        if (a < 0) {
            is1stNegative = true;
            a = -a;
        }
        boolean is2ndNegative = false;
        if (divisorL < 0) {
            is2ndNegative = true;
            divisorL = -divisorL;
        }
        long ans = 0;
        // 特例
        if (divisorL == 1) {
            ans = a;
        }
        // 减法
        else {
            while (divisorL <= a) {
                ans++;
                a -= divisorL;
            }
        }
        // 正负
        if ((is1stNegative && !is2ndNegative)
                || (!is1stNegative && is2ndNegative)) {
            ans = -ans;
        }
        // 溢出
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    /**
     * 除法
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了5.19% 的用户
     */
    public int divide1(int dividend, int divisor) {
        if (dividend == -2147483648 && divisor == -1) {
            return 2147483647;
        }
        return dividend / divisor;
    }

    /**
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     *
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 解释: 7/-3 = truncate(-2.33333..) = -2
     *
     * -2147483648
     * -1
     * 2147483647
     *
     * 1
     * -1
     * -1
     *
     * -2147483648
     * 1
     * -2147483648
     */
    @Test
    public void test() {
//        int ans = divide(10, 3);
//        int ans = divide(9, 3);
//        int ans = divide(20, 3);
//        int ans = divide(7, -3);
//        int ans = divide(1, -2);
        int ans = divide(-2147483648, -1);
//        int ans = divide(1, -1);
        System.out.println("ans = " + ans);
    }

    @Test
    public void bit() {
        System.out.println(3 << 1);
        System.out.println(3 << 2);
        System.out.println(6 << 1);

        System.out.println(3 << 3);
        System.out.println(3 << 4);

        System.out.println("--------");

        System.out.println(2 << 1);
        System.out.println(2 << 2);
        System.out.println(2 << 3);

        System.out.println(1 << 0);
    }
}
