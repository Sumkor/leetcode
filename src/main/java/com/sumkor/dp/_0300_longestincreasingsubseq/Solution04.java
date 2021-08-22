package com.sumkor.dp._0300_longestincreasingsubseq;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/18
 */
public class Solution04 {

    /**
     * 动态规划
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     *
     * 状态定义：
     *     dp[i] 的值代表 nums 前 i 个数字的最长子序列长度。
     *
     * 转移方程： 设 j∈[0,i)，考虑每轮计算新 dp[i] 时，遍历 [0,i) 列表区间，做以下判断：
     *     当 nums[i] >  nums[j] 时： nums[i] 可以接在 nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j]+1。
     *     当 nums[i] <= nums[j] 时： nums[i] 无法接在 nums[j] 之后，此情况上升子序列不成立，跳过。
     *
     *     上述情况下计算出的 dp[j]+1 的最大值，为直到 i 的最长上升子序列长度（即 dp[i]）。实现方式为遍历 j 时，每轮执行 dp[i]=max(dp[i],dp[j]+1)。
     *     转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
     *
     * 初始状态：
     *     dp[i] 所有元素置 1，含义是每个元素都至少可以单独成为子序列，此时长度都为 1。
     *
     * 返回值：
     *     返回 dp 列表最大值，即可得到全局最长上升子序列长度。
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) { // j 在 i 之前
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // dp[i] 的值代表 nums 前 i 个数字的最长子序列长度
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    @Test
    public void test() {
//        int[] prices = {0, 1, 0, 3, 2, 3};
        int[] prices = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] prices = {7, 7, 7, 7, 7, 7, 7};
        int maxProfit = lengthOfLIS(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}
