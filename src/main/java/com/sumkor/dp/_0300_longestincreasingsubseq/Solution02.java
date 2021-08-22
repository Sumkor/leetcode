package com.sumkor.dp._0300_longestincreasingsubseq;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/19
 */
public class Solution02 {

    private int[] cache;

    /**
     * 记忆化搜索（空间换时间）
     *
     * 执行用时：141 ms, 在所有 Java 提交中击败了5.00% 的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了84.52% 的用户
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        cache = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int result = exec(nums, i);
            maxLength = Math.max(maxLength, result);
        }
        return maxLength;
    }

    // 定义函数，可返回数组 i 位置的最长递增子序列长度
    private int exec(int[] nums, int i) {
        if (i == nums.length - 1) {
            return 1;
        }
        // 命中缓存，则不必重复计算
        if (cache != null && cache[i] != 0) {
            return cache[i];
        }
        int maxLength = 1;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                int currLength = exec(nums, j) + 1;
                maxLength = Math.max(maxLength, currLength);
                cache[i] = maxLength; // 加入缓存
            }
        }
        return maxLength;
    }

    @Test
    public void test() {
        int[] prices = {0, 1, 0, 3, 2, 3};
//        int[] prices = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] prices = {7, 7, 7, 7, 7, 7, 7};
        int maxProfit = lengthOfLIS(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}
