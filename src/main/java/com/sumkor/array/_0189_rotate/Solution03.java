package com.sumkor.array._0189_rotate;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/20
 */
public class Solution03 {

    /**
     * 数组翻转
     * https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode-solution-nipk/
     *
     * 该方法基于如下的事实：
     * 当我们将数组的元素向右移动 k 次后，尾部 k mod n 个元素会移动至数组头部，其余元素向后移动 k mod n 个位置。
     *
     * 该方法为数组的翻转：
     * 我们可以先将所有元素翻转，这样尾部的 k mod n 个元素就被移至数组头部，
     * 然后我们再翻转 [0, k mod n − 1] 区间的元素和 [k mod n, n − 1] 区间的元素即能得到最后的答案。
     *
     * 我们以 n=7，k=3 为例进行如下展示：
     *
     *            操作 	                     结果
     * 原始数组 	                        1 2 3 4 5 6 7
     * 翻转所有元素 	                    7 6 5 4 3 2 1
     * 翻转 [0, k mod n − 1] 区间的元素 	5 6 7 4 3 2 1
     * 翻转 [k mod n, n − 1] 区间的元素 	5 6 7 1 2 3 4
     *
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    @Test
    public void test() {
        int[] nums = {-1, -100, 3, 99}; int k = 2;
//        int[] nums = {1, 2, 3, 4, 5, 6, 7}; int k = 3;
        rotate(nums, k);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
