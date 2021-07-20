package com.sumkor.array._0300_lengthoflis;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/19
 */
public class Solution03 {

    private int[] cache;

    /**
     * 动态规划
     *
     * 对于一个长度为 5 的数组，如 [0, 1, 0, 3, 2]
     * 使用函数 L(i) 表示数组 i 位置的最长递增子序列长度
     * 可以得到
     * L(0) = max{L(1), L(2), L(3), L(4)} + 1
     * L(1) = max{L(2), L(3), L(4)} + 1
     * L(2) = max{L(3), L(4)} + 1
     * L(3) = max{L(4)} + 1
     * L(4) = 1
     * 因此，需要从后往前计算(相当于先把递归的最后一层算出来)。
     *
     * 时间复杂度 O(n^2)
     *
     * 执行用时：65 ms, 在所有 Java 提交中击败了72.92% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了53.66% 的用户
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = nums.length - 1; i >= 0; i--) { // i -> 4,3,2,1,0
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // dp[i] 表示数组从 i 位置开始的最长递增子序列长度
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }


    @Test
    public void test() {
//        int[] prices = {0, 1, 0, 3, 2};
//        int[] prices = {0, 1, 0, 3, 2, 3};
        int[] prices = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] prices = {7, 7, 7, 7, 7, 7, 7};
        int maxProfit = lengthOfLIS(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}
