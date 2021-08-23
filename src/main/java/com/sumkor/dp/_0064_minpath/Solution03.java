package com.sumkor.dp._0064_minpath;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/23
 */
public class Solution03 {

    /**
     * 动态规划
     *
     * dp[i][j] 表示以 i，j 位置为【起点】的最短路径
     * 状态转移方程：
     * dp[i][j] = min{dp[i+1][j], dp[i][j+1]} + nums[i][j]
     *
     * 时间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。需要对整个网格遍历一次，计算 dp 的每个元素的值。
     * 空间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。创建一个二维数组 dp，和网格大小相同。
     * 空间复杂度可以优化，例如每次只存储上一行的 dp 值，则可以将空间复杂度优化到 O(n)
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了64.80% 的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了78.73% 的用户
     */
    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int length = grid[0].length;
        int[][] dp = new int[height][length];
        // 初始化终点
        dp[height - 1][length - 1] = grid[height - 1][length - 1];
        // 初始化最后一列
        for (int i = height - 2; i >= 0; i--) {
            dp[i][length - 1] = grid[i][length - 1] + dp[i + 1][length - 1];
        }
        // 初始化最后一行
        for (int i = length - 2; i >= 0; i--) {
            dp[height - 1][i] = grid[height - 1][i] + dp[height - 1][i + 1];
        }
        // 动态规划
        for (int i = height - 2; i >= 0; i--) {
            for (int j = length - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划（官方题解）
     * https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode-solution/
     *
     * dp[i][j] 表示以 i，j 位置为【终点】的最短路径
     * 区别在于，不使用递减。。
     */
    public int minPathSum0(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    /**
     * 动态规划（空间优化版本）
     * https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-dong-tai-gui-hua-gui-fan-liu-c/
     *
     * dp[i][j] 表示以 i，j 位置为【终点】的最短路径
     *
     * 其实我们完全不需要建立 dp 矩阵浪费额外空间，直接遍历 grid[i][j] 修改即可。
     * 这是因为：grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]；
     * 原 grid 矩阵元素中被覆盖为 dp 元素后（都处于当前遍历点的左上方），不会再被使用到。
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了64.80% 的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了90.25% 的用户
     */
    public int minPathSum1(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                // 只能往右
                else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                }
                // 只能往下
                else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                }
                // 往右或往下
                else {
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    /**
     * [1,3,1]
     * [1,5,1]
     * [4,2,1]
     * 7
     *
     * [1,2,3]
     * [4,5,6]
     * 12
     */
    @Test
    public void test() {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int result = minPathSum(grid);
        System.out.println("result = " + result);
    }
}
