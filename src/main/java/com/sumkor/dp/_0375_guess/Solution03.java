package com.sumkor.dp._0375_guess;

/**
 * @author Sumkor
 * @since 2021/9/24
 */
public class Solution03 {

    /**
     * 动态规划（官方题解）
     * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/cai-shu-zi-da-xiao-ii-by-leetcode/
     *
     * 以 i 为第一次尝试找到最小开销的过程可以被分解为找左右区间内最小开销的子问题。
     * 对于每个区间，我们重复问题拆分的过程，得到更多子问题，这启发我们可以用 DP 解决这个问题。
     *
     * 使用 dp(i,j) 代表在 (i,j) 中最坏情况下最小开销的代价。
     * 如果区间只剩下一个数 k ，那么猜中的代价永远为 0。
     *
     */
}
