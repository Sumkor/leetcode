package com.sumkor.array._0001_twosum;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/21
 */
public class Solution {

    /**
     * 暴力
     *
     * 执行用时: 91 ms
     * 内存消耗: 39.1 MB
     *
     * 时间复杂度：O(N^2)，其中 N 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1)。
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 双指针，本质也是暴力
     *
     * 执行用时：80 ms, 在所有 Java 提交中击败了5.12% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了22.16% 的用户
     */
    public int[] twoSum0(int[] nums, int target) {
        int i = 0;
        int j = 1;
        while (i < j) {
            if (j == nums.length) {
                i++;
                j = i + 1;
                continue;
            }
            if (nums[i] + nums[j] == target) {
                return new int[]{i, j};
            } else {
                j++;
            }
        }
        return new int[0];
    }


    @Test
    public void test() {
//        int[] nums = {3, 2, 4}; int target = 6;
//        int[] nums = {3, 3}; int target = 6;
        int[] nums = {2, 7, 11, 15}; int target = 9;
        nums = twoSum(nums, target);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
