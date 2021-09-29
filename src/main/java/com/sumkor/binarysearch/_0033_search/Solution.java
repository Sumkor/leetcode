package com.sumkor.binarysearch._0033_search;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/29
 */
public class Solution {

    /**
     * 搜索区间 [left, right]
     * 终止条件 left = right + 1
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) { // 4,5,6,1,2 - 2   7,1,2,3,4 - 1
                // 找左边
                int midL = left + (mid - left) / 2;
                if (nums[midL] == target) {
                    return midL;
                } else if (nums[midL] < target) {
                    // 说明在 [midL, mid] 范围中
                    continue;
                } else if (nums[midL] > target) {
                    // 找右边
                    int midR = mid + (right - mid) / 2;
                    if (nums[midR] == target) {
                        return midR;
                    } else if (nums[midR] > target) {
                        // TODO
                    } else {
                        // TODO
                    }
                }

            }
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
     */
    @Test
    public void test() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        int result = search(nums, target);
        System.out.println("result = " + result);
    }
}
