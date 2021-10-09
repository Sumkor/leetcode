package com.sumkor.math._0066_plusone;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/7/21
 */
public class Solution {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了95.90% 的用户
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        // 溢出
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        // System.arraycopy(digits, 0, newDigits, 1, digits.length);
        return newDigits;
    }

    @Test
    public void test() {
        int[] nums = {9, 9, 9, 9};
        nums = plusOne(nums);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
