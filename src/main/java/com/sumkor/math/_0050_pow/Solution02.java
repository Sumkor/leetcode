package com.sumkor.math._0050_pow;

/**
 * @author Sumkor
 * @since 2021/10/4 下午3:18
 */
public class Solution02 {

    /**
     * 快速幂算法（官方题解）
     * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     *
     *「快速幂算法」的本质是分治算法。
     *
     * 举个例子，如果我们要计算 x^64 ，我们可以按照：
     * x -> x^2 -> x^4 -> x^8 -> x^16 -> x^32 -> x^64
     * 的顺序，从 x 开始，每次直接把上一次的结果进行平方，计算 6 次就可以得到 x^64，而不用计算 46 次。
     *
     * 再举一个例子，如果我们要计算 x^77，我们可以按照：
     * x -> x^2 -> x^4 -> x^9 -> x^19 -> x^38 -> x^77
     *
     * 计算过程：
     * 当我们要计算 x^n 时，我们可以先递归地计算出 x^⌊n/2⌋，其中 ⌊a⌋ 表示对 a 进行向下取整。
     * 根据递归计算的结果，如果 n 为偶数，那么 x^n = y^2，如果 n 为奇数，那么 x^n = y^2 * x。
     * 递归的边界为 n = 0。
     *
     * 时间复杂度：O(logn)，即为递归的层数。
     * 空间复杂度：O(logn)，即为递归的层数，这是由于递归时需要使用栈空间。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了 90.47% 的用户
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

}
