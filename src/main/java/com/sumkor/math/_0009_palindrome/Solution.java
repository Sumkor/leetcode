package com.sumkor.math._0009_palindrome;

import org.junit.Test;

/**
 * @author Sumkor
 * @since  2021/10/4 下午5:35
 */
public class Solution {

    /**
     * 整数反转，再对比
     *
     * 执行用时：8 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了 69.41% 的用户
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int origin = x;
        long n = 0;
        while (x > 0) {
            int curr = x % 10;
            n = n * 10 + curr;
            x = x / 10;
        }
        return n == origin;
    }

    /**
     * 输入：x = 121
     * 输出：true
     */
    @Test
    public void test() {
        boolean palindrome = isPalindrome(121);
        System.out.println("palindrome = " + palindrome);
    }
}
