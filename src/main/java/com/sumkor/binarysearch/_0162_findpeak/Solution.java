package com.sumkor.binarysearch._0162_findpeak;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/30
 */
public class Solution {

    /**
     * 二分法
     *
     * 题目中：
     * 假设 nums[-1] = nums[n] = -∞
     * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
     *
     * 如果允许 nums[i] == nums[i + 1] 则本解法是有问题的，例如输入 nums = [2, 2, 2, 0, 1] 得到 -1，实际应该是 4
     *
     * 基本思路：如果我们从一个位置开始，不断地向高处走，那么最终一定可以到达一个峰值位置。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了57.49% 的用户
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 中间
            int leftValue = mid > 0 ? nums[mid - 1] : Integer.MIN_VALUE;
            int rightValue = mid < n - 1 ? nums[mid + 1] : Integer.MIN_VALUE;
            if (leftValue < nums[mid] && nums[mid] > rightValue) {
                return mid;
            }
            // 左右两边都可选，这里选择当前较大的一边，出现峰值的可能性较高！
            else if (leftValue <= nums[mid] && nums[mid] <= rightValue) {
                left = mid + 1;
            }
            else if (leftValue >= nums[mid] && nums[mid] >= rightValue) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分法（简化写法）
     *
     * 来源：官方题解评论区
     * https://leetcode-cn.com/problems/find-peak-element/solution/xun-zhao-feng-zhi-by-leetcode-solution-96sj/
     *
     * 时间复杂度：O(logn)，其中 n 是数组 nums 的长度。
     * 空间复杂度：O(1)。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了50.38% 的用户
     */
    public int findPeakElement01(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 由于终止条件为 left < right，因此 mid 不会是最后一个位置，因此 mid + 1 不会越界
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1; // mid 不满足，需过滤
            } else {
                right = mid;    // mid 已满足，需保留
            }
        }
        return left;
    }

    /**
     * 输入：nums = [1,2,3,1]
     * 输出：2
     *
     * 输入：nums = [1,2,1,3,5,6,4]
     * 输出：1 或 5
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        int peak = findPeakElement01(nums);
        System.out.println("peak = " + peak);
    }
}
