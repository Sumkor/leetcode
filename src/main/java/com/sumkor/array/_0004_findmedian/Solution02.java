package com.sumkor.array._0004_findmedian;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/14
 */
public class Solution02 {

    /**
     * 中位数，排序为中间的数
     * 1. 根据数组长度，计算中位数的位置
     * 2. 同时遍历两个数组，找到中间的数
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.99% 的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了90.71% 的用户
     *
     * 时间复杂度：O((m+n)/2)
     * 空间复杂度：O((m+n)/2)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0d;
        int m = nums1.length;
        int n = nums2.length;
        int sum = m + n;
        // 是否偶数
        boolean sign = sum % 2 == 0;
        // 中位数的下标
        int mid = sum >> 1;
        int[] nums = new int[mid + 1];
        // 同时遍历两个数组
        int i = 0;
        int j = 0;
        while (i + j <= mid) {
            int index = i + j;
            // nums1遍历完了
            if (i == m) {
                nums[index] = nums2[j];
                j++;
                continue;
            }
            // nums2遍历完了
            if (j == n) {
                nums[index] = nums1[i];
                i++;
                continue;
            }
            // 同时遍历
            if (nums1[i] <= nums2[j]) {
                nums[index] = nums1[i];
                i++;
            } else {
                nums[index] = nums2[j];
                j++;
            }
        }
        // 中位数
        if (sign) {
            result = (nums[mid - 1] + nums[mid]) / 2d;
        } else {
            result = nums[mid];
        }
        return result;
    }

    /**
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     *
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     *
     * 输入：nums1 = [0,0], nums2 = [0,0]
     * 输出：0.00000
     *
     * 输入：nums1 = [], nums2 = [1]
     * 输出：1.00000
     *
     * 输入：nums1 = [2], nums2 = []
     * 输出：2.00000
     */
    @Test
    public void test() {
//        int[] nums1 = new int[]{1, 2};
//        int[] nums2 = new int[]{3, 4};

//        int[] nums1 = new int[]{1, 3};
//        int[] nums2 = new int[]{2};

//        int[] nums1 = new int[]{};
//        int[] nums2 = new int[]{1};

        int[] nums1 = new int[]{2};
        int[] nums2 = new int[]{};

        double ans = findMedianSortedArrays(nums1, nums2);
        System.out.println("ans = " + ans);
    }
}
