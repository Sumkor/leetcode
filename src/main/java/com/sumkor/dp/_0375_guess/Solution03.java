package com.sumkor.dp._0375_guess;

import org.junit.Assert;
import org.junit.Test;

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
     * 如果区间只剩下一个数 k ，那么猜中的代价永远为 0。也就是说，所有的 dp(k,k) 都初始化为 0。
     * 然后，对于长度为 2 的区间，我们需要所有长度为 1 的区间的结果。由此我们可以看出，为了求出长度为 len 区间的解，我们需要所有长度为 len−1 的解。
     *
     * 对于每个 dp(i,j)，当前长度为 len = j-i+1。从 i 到 j 范围依次挑选每个数字作为第一次尝试的答案，可以求出最小开销：
     * cost(i,j) = pivot + max(cost(i,pivot−1), cost(pivot+1,n))
     *
     * 但是在计算开销的时候我们有一个便利之处，就是我们已经知道了小于 len 长度 dp 数组的所有答案。因此 dp 方程式变成了：
     * dp(i,j) = min_pivots(i,j) [pivot + max(dp(i,pivot−1), dp(pivot+1,n))]
     *
     * 其中，min_pivots(i,j) 表示依次将 (i,j) 中的每个数作为第一个尝试的数 pivot，并在所有计算结果中取最小值。
     *
     * 时间复杂度： O(n^3) 。我们遍历 dp 数组一遍需要 O(n^2) 的时间开销。对于数组中每个元素，我们最多需要遍历 n 个数字。
     * 空间复杂度： O(n^2) 。需要创建 n^2 空间的 dp数组。
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minResult = Integer.MAX_VALUE;
                for (int pivot = start; pivot < start + len - 1; pivot++) {
                    int res = pivot + Math.max(dp[start][pivot - 1], dp[pivot + 1][start + len - 1]);
                    minResult = Math.min(res, minResult);
                }
                dp[start][start + len - 1] = minResult;
            }
        }
        return dp[1][n];
    }


    /**
     * 优化后的动态规划
     *
     * 在上一个方法中，我们尝试使用 (i ,j) 中的每一个数作为第一个选的数。
     * 实际上，只需要从 (i+(len-1)/2 ,j) 中选第一个数就可以了，其中 len 是当前区间的长度
     *
     * 时间复杂度： O(n^3) 。我们遍历 dp 数组一遍需要 O(n^2) 的时间开销。对于数组中每个元素，我们最多需要遍历 n/2 个数字。
     * 空间复杂度： O(n^2) 。需要创建 n^2 空间的 dp数组。
     *
     * 执行用时：11 ms, 在所有 Java 提交中击败了97.22% 的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了88.13% 的用户
     */
    public int getMoneyAmount1(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minres = Integer.MAX_VALUE;
                for (int piv = start + (len - 1) / 2; piv < start + len - 1; piv++) {
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    minres = Math.min(res, minres);
                }
                dp[start][start + len - 1] = minres;
            }

        }
        return dp[1][n];
    }

    @Test
    public void test() {
        int n = 4;
        int moneyAmount = getMoneyAmount(n);
        Assert.assertEquals(4, moneyAmount);
    }
}
