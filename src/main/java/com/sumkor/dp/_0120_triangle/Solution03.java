package com.sumkor.dp._0120_triangle;

import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/22
 */
public class Solution03 {

    /**
     * 动态规划
     *
     * dp[i][j] 表示第 i 层的第 j 个下标位置为起点的最短路径和
     *
     * 状态转移方程：
     * dp[i][j] = min{dp[i+1][j], dp[i+1][j+1]} + nums[i][j]
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了95.03% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了34.27% 的用户
     *
     * 时间复杂度：O(N^2)，N 为三角形的行数。
     * 空间复杂度：O(N^2)，N 为三角形的行数。
     *
     * {@link TestCase#testLong()} 耗时 8 ms
     */
    public int minimumTotal0(List<List<Integer>> triangle) {
        int height = triangle.size();
        List<Integer> lastList = triangle.get(height - 1);
        int[][] dp = new int[height][lastList.size()];
        // 初始化最后一层
        for (int i = 0; i < lastList.size(); i++) {
            dp[height - 1][i] = lastList.get(i);
        }
        // 倒推得到第 1 层
        for (int i = height - 2; i >= 0; i--) {
            int size = triangle.get(i).size();
            for (int j = 0; j < size; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划，简化版本
     *
     * {@link TestCase#testLong()} 耗时 3 ms
     */
    public int minimumTotal1(List<List<Integer>> triangle) {
        int height = triangle.size();
        // 在最后一层之下，增加多一层，初始化为 0
        int[][] dp = new int[height + 1][height + 1];
        // 倒推得到第 1 层
        for (int i = height - 1; i >= 0; i--) {
            int size = triangle.get(i).size();
            for (int j = 0; j < size; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划，空间优化版本
     *
     * https://leetcode-cn.com/problems/triangle/solution/di-gui-ji-yi-hua-dp-bi-xu-miao-dong-by-sweetiee/
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了95.03% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了35.37% 的用户
     *
     * 时间复杂度：O(N^2)，N 为三角形的行数。
     * 空间复杂度：O(N)，N 为三角形的行数。
     *
     * {@link TestCase#testLong()} 耗时 3 ms
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        // 在最后一层之下，增加多一层，初始化为 0
        int[] dp = new int[height + 1];
        // 倒推得到第 1 层
        for (int i = height - 1; i >= 0; i--) {
            int size = triangle.get(i).size();
            for (int j = 0; j < size; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

}
