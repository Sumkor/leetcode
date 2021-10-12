package com.sumkor.dp._0062_uniquepath;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/12
 */
public class Solution03 {

    /**
     * 动态规划
     *
     * dp[i][j] 表示以 i，j 位置为【终点】的不同路径数量
     *
     * 状态转移方程：
     * dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     *
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了72.75% 的用户
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        // 初始状态
        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= n; j++) {
            dp[1][j] = 1;
        }
        // 状态转移
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * 输入：m = 3, n = 7
     * 输出：28
     *
     * 输入：m = 3, n = 2
     * 输出：3
     */
    @Test
    public void test() {
        int m = 3, n = 2;
        int paths = uniquePaths(m, n);
        System.out.println("paths = " + paths);
    }
}
