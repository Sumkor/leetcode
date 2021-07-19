package com.sumkor.array.lengthoflis;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/19
 */
public class Solution01 {

    /**
     * 暴力搜索
     * https://www.bilibili.com/video/BV1AB4y1w7eT?from=search&seid=11859218109692248080
     *
     * 时间复杂度：O(2^n)*O(n) = O(n*2^n)，其中 n 为数组的长度
     * O(2^n) 表示存在的子序列个数。因为数组中的每一个数，可以取也可以不取。
     * O(n) 是因为每个子序列至多遍历 n 次，去判断是否符合递增序列
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 1;
        for (int i = 0; i < nums.length; i++) {
            int result = exec(nums, i);
            maxLength = Math.max(maxLength, result);
        }
        return maxLength;
    }

    // 定义函数，可返回数组 i 位置的最长递增子序列长度
    private int exec(int[] nums, int i) {
        // 如果 i 是最后一个数字，直接返回
        if (i == nums.length - 1) {
            return 1;
        }
        int maxLength = 1;
        // 检查 i 位置后的所有数字
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                int currLength = exec(nums, j) + 1; // 递归计算从 j 开始的最长递增子序列长度，再 +1 得到目前的总长度
                maxLength = Math.max(maxLength, currLength); // 遍历所有 j 选择出最长的长度
            }
        }
        return maxLength;
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
