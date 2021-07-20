package com.sumkor.array._0027_remove;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/20
 */
public class Solution {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：37.2 MB, 在所有 Java 提交中击败了8.26% 的用户
     */
    public int removeElement(int[] nums, int val) {
        int newLength = nums.length;
        for (int i = 0; i < newLength; i++) {
            if (nums[i] == val) {
                if (i + 1 < newLength) {
                    // i后面的数全部向前移动一位
                    System.arraycopy(nums, i + 1, nums, i, newLength - (i + 1));
                } else {
                    nums[i] = 0;
                }
                // 下一次还是遍历 i
                i--;
                newLength--;
            }
        }
        return newLength;
    }

    @Test
    public void test() {
//        int[] nums = {3, 2, 2, 3}; int val = 3;
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2}; int val = 2;
        int newLength = removeElement(nums, val);
        System.out.println("newLength = " + newLength);
        Arrays.stream(nums).forEach(System.out::print);
    }

    @Test
    public void arrayCopy() {
        int[] nums = {0, 1, 2, 3, 4};
        // 删除 index 为 1 的值
        System.arraycopy(nums, 2, nums, 1, 3);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
