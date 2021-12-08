package com.sumkor.dp._0509_fibonacci;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/12/7
 */
public class Solution {

    /**
     * 动态规划
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了86.24% 的用户
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了5.45% 的用户
     */
    public int fib01(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0;
        int b = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }


    /**
     * 输入：2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     *
     * 输入：3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     *
     * 输入：4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     */
    @Test
    public void test() {
        int n = 17;
        int fib = fib(n);
        System.out.println("fib = " + fib);

        int fib01 = fib01(n);
        System.out.println("fib01 = " + fib01);
    }
}
