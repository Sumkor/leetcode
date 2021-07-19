package com.sumkor.array.lengthoflis;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/19
 */
public class Solution01 {

    /**
     * 暴力
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        recur(nums, 0, 1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println("nums[i] = " + nums[i]);
        }
        return 0;
    }

    private int recur(int[] nums, int index, int count) {
        for (int i = index; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    recur(nums, j, count + 1);
                    // TODO
                    break;
                }
            }
        }
        return count;
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
