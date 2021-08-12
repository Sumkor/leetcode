package com.sumkor.dp._0070_climbstairs;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/12
 */
public class Solution {

    /**
     * 第 1 级台阶：1 种方法（爬 1 级）
     * 第 2 级台阶：2 种方法（爬 1 级或爬 2 级）
     * 第 n 级台阶：从第 n-1 级台阶爬 1 级，或从第 n-2 级台阶爬 2 级
     *
     * 状态转移方程：dp[i] = dp[i-1] + dp[i-2]
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了37.91% 的用户
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1]; // 记录状态，从 1 到 n 依次更新
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
     *
     * 动态规划 + 滚动数组
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int climbStairs0(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i < n + 1; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    /**
     * 1 : 1
     * 2 : 2
     * 3 : 3
     * 4 : 5
     * 5 : 8
     */
    @Test
    public void test() {
        int result;
        result = climbStairs(1);
        result = climbStairs(2);
        result = climbStairs(3);
        result = climbStairs(4);
        result = climbStairs(5);
        System.out.println("result = " + result);
    }
}
