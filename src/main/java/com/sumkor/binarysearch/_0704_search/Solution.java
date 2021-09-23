package com.sumkor.binarysearch._0704_search;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/23
 */
public class Solution {

    /**
     * 二分法（标准写法）
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了75.63% 的用户
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        int target = 5;

        int i = search(nums, target);
        System.out.println("i = " + i);
    }
}
