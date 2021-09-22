package com.sumkor.binarysearch._0069_sqrtx;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/9/22
 */
public class Solution {

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.5 MB, 在所有 Java 提交中击败了59.00% 的用户
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long min = 1;
        long max = x;
        while (min + 1 < max) {
            long mid = (max + min) / 2;
            long ans = mid * mid;
            if (ans == x) {
                return (int) mid;
            } else if (ans < x) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return (int) min;
    }

    /**
     * 输入：x = 4
     * 输出：2
     *
     * 输入：x = 8
     * 输出：2
     */
    @Test
    public void test() {
//        int x = 5;
//        int x = 8;
//        int x = 1;
//        int x = 0;
        int x = 2147395599;

        int pow = (int) Math.pow(x, 0.5);
        System.out.println("pow = " + pow);

        int ans = mySqrt(x);
        System.out.println("ans = " + ans);
    }
}
