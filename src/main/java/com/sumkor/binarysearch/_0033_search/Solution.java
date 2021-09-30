package com.sumkor.binarysearch._0033_search;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/29
 */
public class Solution {

    /**
     * 二分法
     *
     * 搜索区间 [left, right]
     * 终止条件 left + 1 = right
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了66.63% 的用户
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) { // 4,5,6,1,2 - 2   7,1,2,3,4 - 1
                // 如果左边是递增区间
                if (nums[left] < nums[mid]) {
                    if (nums[left] == target) {
                        return left;
                    }
                    // 确定不在左边
                    else if (nums[left] > target) {
                        left = mid + 1;
                    }
                    // 确定在左边
                    else if (nums[left] < target) {
                        right = mid - 1;
                    }
                }
                // 如果右边是递增区间
                else if (nums[right] > nums[mid]) {
                    // 确定不在右边
                    right = mid - 1;
                }
            } else if (nums[mid] < target) { // 4,5,6,7,2 - 7   7,1,2,3,4 - 7
                // 如果左边是递增区间
                if (nums[left] < nums[mid]) {
                    // 确定不在左边
                    left = mid + 1;
                }
                // 如果右边是递增区间
                else if (nums[right] > nums[mid]) {
                    if (nums[right] == target) {
                        return right;
                    }
                    // 确定不在右边
                    else if (nums[right] < target) {
                        right = mid - 1;
                    }
                    // 确定在右边
                    else if (nums[right] > target) {
                        left = mid + 1;
                    }
                }
            }
        }
        // 终止条件 left + 1 = right
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        return -1;
    }

    /**
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *
     * [3,1] 0
     */
    @Test
    public void test() {
//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
//        int target = 0;

        int[] nums = new int[]{1};
        int target = 0;

//        int[] nums = new int[]{3, 1};
//        int target = 0;

        int result = search(nums, target);
        System.out.println("result = " + result);
    }
}
