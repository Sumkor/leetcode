package com.sumkor.array._0122_stock;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/7/18
 */
public class Solution02 {

    /**
     * 力扣官方题解
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
     *
     * 动态规划
     *
     * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
     * 定义状态 dp[i][0] 表示第 i 天交易完后手里【没有】股票的最大利润，dp[i][1] 表示第 i 天交易完后手里【持有】一支股票的最大利润（i 从 0 开始）。
     *
     * 考虑 dp[i][0] 的转移方程，
     * 如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]，
     * 或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i] 的收益。
     * 因此为了收益最大化，我们列出如下的转移方程：
     * dp[i][0] = max{dp[i−1][0], dp[i−1][1]+prices[i]}
     *
     * 再来考虑 dp[i][1]，按照同样的方式考虑转移状态，那么
     * 可能的转移状态为前一天已经持有一支股票，即 dp[i−1][1]，
     * 或者前一天结束时还没有股票，即 dp[i−1][0]，这时候我们要将其买入，并减少 prices[i] 的收益。
     * 可以列出如下的转移方程：
     * dp[i][1] = max{dp[i−1][1], dp[i−1][0]−prices[i]}
     *
     * 对于初始状态，根据状态定义我们可以知道第 0 天交易结束的时候 dp[0][0] = 0，dp[0][1] = −prices[0]。
     * 因此，我们只要从前往后依次计算状态即可。
     *
     * 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，
     * 因此这时候 dp[n−1][0] 的收益必然是大于 dp[n−1][1] 的，最后的答案即为 dp[n−1][0]。
     */
    public int maxProfit0(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); // 表示第 i 天交易完后手里【没有】股票的最大利润
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); // 表示第 i 天交易完后手里【持有】一支股票的最大利润
        }
        return dp[n - 1][0];
    }

    /**
     * 力扣官方题解
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
     *
     * 动态规划 优化版

     * 注意到上面的状态转移方程中，每一天的状态只与前一天的状态有关，而与更早的状态都无关，
     * 因此我们不必存储这些无关的状态，只需要将 dp[i−1][0] 和 dp[i−1][1] 存放在两个变量中，
     * 通过它们计算出 ddp[i][0] 和 dp[i][1] 并存回对应的变量，以便于第 i+1 天的状态转移即可。
     *
     * 时间复杂度：O(n)，其中 n 为数组的长度。一共有 2n 个状态，每次状态转移的时间复杂度为 O(1)，因此时间复杂度为 O(2n)=O(n)。
     * 空间复杂度：O(n)。我们需要开辟 O(n) 空间存储动态规划中的所有状态。如果使用空间优化，空间复杂度可以优化至 O(1)。
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;
    }

    @Test
    public void test() {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {7, 6, 4, 3, 1};
//        int[] prices = {2, 4, 1};
        int[] prices = {1, 2, 3, 4, 5};
        int maxProfit = maxProfit(prices);
        System.out.println("maxProfit = " + maxProfit);
    }
}
