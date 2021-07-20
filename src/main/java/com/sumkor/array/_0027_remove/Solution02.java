package com.sumkor.array._0027_remove;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/20
 */
public class Solution02 {

    /**
     * 比较简单的解法，newLength 从 0 开始算
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了35.41% 的用户
     */
    public int removeElement(int[] nums, int val) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }

    @Test
    public void test() {
        int[] nums = {3, 2, 2, 3}; int val = 3;
        int newLength = removeElement(nums, val);
        System.out.println("newLength = " + newLength);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
