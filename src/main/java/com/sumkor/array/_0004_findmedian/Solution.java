package com.sumkor.array._0004_findmedian;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/10/14
 */
public class Solution {

    /**
     * 暴力法
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int sum = m + n;
        int[] nums = new int[sum];
        System.arraycopy(nums1, 0, nums, 0, m);
        System.arraycopy(nums2, 0, nums, m, n);
        Arrays.sort(nums);
        double result = 0d;
        int index = sum / 2;
        if (sum % 2 == 0) {
            result = (nums[index - 1] + nums[index]) / 2d;
        } else {
            result = nums[index];
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

        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{1};

        double ans = findMedianSortedArrays(nums1, nums2);
        System.out.println("ans = " + ans);
    }
}
