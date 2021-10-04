package com.sumkor.math._0007_reverse;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/4 下午4:13
 */
public class Solution {

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了 72.18% 的用户
     */
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        long X = x;
        // 负数
        boolean isNegative = false;
        if (X < 0) {
            isNegative = true;
            X = -X;
        }
        // 反转
        long ans = 0;
        while (X > 0) {
            long curr = X % 10;
            ans = ans * 10 + curr;
            X = X / 10;
        }
        // 溢出
        if (ans > (long) Integer.MAX_VALUE) {
            ans = 0;
        }
        return (int) (isNegative ? -ans : ans);
    }

    /**
     * 简洁写法
     */
    public int reverse0(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }

    /**
     * 输入：x = 123
     * 输出：321
     *
     * 输入：x = -123
     * 输出：-321
     *
     * 输入：x = 120
     * 输出：21
     *
     * 输入：x = 0
     * 输出：0
     *
     * 1534236469
     * 0
     */
    @Test
    public void test() {
//        int ans = reverse(123);
        int ans = reverse0(-123);
//        int ans = reverse(120);
//        int ans = reverse(0);
//        int ans = reverse0(1534236469);
        System.out.println("ans = " + ans);
    }
}
