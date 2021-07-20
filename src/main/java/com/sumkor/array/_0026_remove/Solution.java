package com.sumkor.array._0026_remove;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/20
 */
public class Solution {

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了85.61% 的用户
     * 内存消耗：39.3 MB, 在所有 Java 提交中击败了99.00% 的用户
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[index]) { // 由于是有序的，只要相等，则一直覆盖在 index 位置
                nums[index] = nums[i];
            } else {
                nums[++index] = nums[i];  // 不相等时，index 先自增，再赋值
            }
        }
        return index + 1;
    }

    @Test
    public void test() {
//        int[] nums = {2, 2, 3, 3};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int newLength = removeDuplicates(nums);
        System.out.println("newLength = " + newLength);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
