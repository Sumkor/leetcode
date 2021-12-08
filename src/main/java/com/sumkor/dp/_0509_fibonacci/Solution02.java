package com.sumkor.dp._0509_fibonacci;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/12/7
 */
public class Solution02 {

    /**
     * 递归
     * 递归只是实现分治的手段。分治 是自顶而下；动规 是自底而上
     *
     * 执行用时：8 ms, 在所有 Java 提交中击败了22.54% 的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了83.32% 的用户
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n - 1) + fib(n - 2);
    }


    /**
     * 递归 + 剪枝
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了69.37% 的用户
     */
    public int fib01(int n) {
        int[] cache = new int[n + 1];
        return recur(n, cache);
    }

    public int recur(int n, int[] cache) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (cache[n] != 0) {
            return cache[n];
        }
        cache[n] = recur(n - 1, cache) + recur(n - 2, cache);
        return cache[n];
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
        int n = 25;
        int fib = fib(n);
        System.out.println("fib = " + fib);

        int fib01 = fib01(n);
        System.out.println("fib01 = " + fib01);
    }
}
