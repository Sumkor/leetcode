package com.sumkor.math._0050_pow;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/4 下午3:45
 */
public class Solution03 {

    /**
     * 快速幂算法（官方题解）
     * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     *
     * 77 对应的二进制 (1001101) 和每个二进制位的权值如下
     *
     *  1     0      0    1    1    0    1
     * x^64  x^32  x^16  x^8  x^4  x^2  x^1
     *
     * 最终结果就是所有二进制位为1的权值之积：x^1 * x^4 * x^8 * x^64 = x^77
     *
     * 时间复杂度：O(logn)，即为递归的层数。
     * 空间复杂度：O(1)。
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    @Test
    public void test() {
        double pow = myPow(2d, 5);
        System.out.println("pow = " + pow);
    }

}
