package com.sumkor.binarysearch._0154_findmin;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/9/30
 */
public class Solution {

    /**
     * 虚假的二分法
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了10.56% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了71.86% 的用户
     */
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 如果左边是有序递增的（这里不能用等号）
            if (nums[left] < nums[mid]) {
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }
            // 如果右边是有序递增的
            else if (nums[mid] < nums[right]) {
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
            // 左右都不是有序递增的
            else if (nums[left] > nums[mid]) {
                // 说明最小值在 [left, mid] 范围中
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
            else if (nums[mid] > nums[right]) {
                // 说明最小值在 [mid, right] 范围中
                min = Math.min(min, nums[right]);
                left = mid + 1;
            }
            else if (nums[mid] == nums[left] && nums[mid] == nums[right]) {
                // 否则，只能排序了
                int[] newNums = Arrays.copyOfRange(nums, left, right + 1);
                Arrays.sort(newNums);
                min = Math.min(min, newNums[0]);
                return min;
            }
        }
        return min;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了10.56% 的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了27.79% 的用户
     */
    public int findMin0(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }

    /**
     * 输入：nums = [1,3,5]
     * 输出：1
     *
     * 输入：nums = [2,2,2,0,1]
     * 输出：0
     *
     * [10,1,10,10,10]
     */
    @Test
    public void test() {
        int[] nums = new int[]{2, 2, 2, 0, 1};
        int min = findMin(nums);
        System.out.println("min = " + min);
    }
}
