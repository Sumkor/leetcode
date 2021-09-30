package com.sumkor.binarysearch._0154_findmin;

/**
 * @author 黄泽滨 【huangzebin@i72.com】
 * @since 2021/9/30
 */
public class Solution02 {

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/154-find-minimum-in-rotated-sorted-array-ii-by-jyd/
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/xun-zhao-xuan-zhuan-pai-xu-shu-zu-zhong-de-zui--16/
     *
     * 思路：
     *
     * 旋转排序数组 nums 可以被拆分为 2 个排序数组 nums1, nums2，并且 nums1 任一元素 >= nums2 任一元素
     * 因此，考虑二分法寻找此两数组的分界点 nums[i] (即第 2 个数组的首个元素)。
     *
     * 当 nums[mid] > nums[right] 时，mid 一定在第 1 个排序数组中，分界点 i 一定满足 mid < i <= right，因此执行 left = mid + 1
     * 当 nums[mid] < nums[right] 时，mid 一定在第 2 个排序数组中，i 一定满足 left < i <= mid，因此执行 right = mid；
     *
     * 当 nums[mid] == nums[right] 时，无法判断 mid 在哪个排序数组中。
     * 采用 right = right - 1 解决此问题，证明：
     * 1. 此操作不会使数组越界
     * 2. 此操作不会使最小值丢失：假设 nums[right] 是最小值，有两种情况：
     *    若 nums[right] 是唯一最小值：那就不可能满足判断条件 nums[mid] == nums[right]，因为由 mid < right（left != right 且 mid = (left + right) // 2 向下取整）可知 mid != right；
     *    若 nums[right] 不是唯一最小值，由于 nums[mid] == nums[right]，即还有最小值存在于 [left,right−1] 区间，因此不会丢失最小值。
     *
     * 时间复杂度：平均时间复杂度为 O(logn)，其中 n 是数组 nums 的长度。
     *           如果数组是随机生成的，那么数组中包含相同元素的概率很低，在二分查找的过程中，大部分情况都会忽略一半的区间。
     *           而在最坏情况下，如果数组中的元素完全相同，那么 while 循环就需要执行 n 次，每次忽略区间的右端点，时间复杂度为 O(n)。
     * 空间复杂度：O(1)
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了70.99% 的用户
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 说明 min 在右区间
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // 说明 min 在左区间
            else if (nums[mid] < nums[right]) {
                right = mid;
            }
            // 缩小右边界，确保不会丢失最小值
            else if (nums[mid] == nums[right]) {
                right = right - 1;
            }
        }
        return nums[left];
    }


}
