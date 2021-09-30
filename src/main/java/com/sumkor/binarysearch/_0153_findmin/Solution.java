package com.sumkor.binarysearch._0153_findmin;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/30
 */
public class Solution {

    /**
     * 二分法
     *
     * 数组本身不是有序的，进行旋转后只保证了数组的局部是有序的。
     * 可以发现的是，我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。
     *
     * 时间复杂度：O(logn)，其中 n 为 nums 数组的大小。在二分查找的过程中，每一步会忽略一半的区间，因此时间复杂度 O(logn)。
     * 空间复杂度：O(1)。我们只需要常数级别的空间存放变量
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了17.85% 的用户
     */
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 如果左边是有序的
            if (nums[left] <= nums[mid]) {
                min = Math.min(min, nums[left]);
                left = mid + 1;
            }
            // 如果右边是有序的
            else {
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
        }
        return min;
    }

    /**
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     *
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     *
     * 输入：nums = [11,13,15,17]
     * 输出：11
     */
    @Test
    public void test() {
//        int[] nums = new int[]{3, 4, 5, 1, 2};
//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] nums = new int[]{11, 13, 15, 17};
        int min = findMin(nums);
        System.out.println("min = " + min);
    }
}
