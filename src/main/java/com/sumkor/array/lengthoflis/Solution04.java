package com.sumkor.array.lengthoflis;

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
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    @Test
    public void test() {
//        int[] prices = {0, 1, 0, 3, 2, 3};
//        int[] prices = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] prices = {7, 7, 7, 7, 7, 7, 7};
        int maxProfit = lengthOfLIS(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}
