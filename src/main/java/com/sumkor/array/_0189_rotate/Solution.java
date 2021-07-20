package com.sumkor.array._0189_rotate;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/20
 */
public class Solution {

    /**
     * 取模运算 辅助数组
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了67.09% 的用户
     * 内存消耗：55.1 MB, 在所有 Java 提交中击败了76.54% 的用户
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。
     * 空间复杂度：O(n)
     */
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[(i + k) % length] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, length);
    }

    @Test
    public void test() {
        int[] nums = {-1, -100, 3, 99}; int k = 2;
//        int[] nums = {1, 2, 3, 4, 5, 6, 7}; int k = 3;
        rotate(nums, k);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
