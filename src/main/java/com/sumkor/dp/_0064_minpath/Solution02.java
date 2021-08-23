package com.sumkor.dp._0064_minpath;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/8/23
 */
public class Solution02 {

    /**
     * 记忆化搜索
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.78% 的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了35.51% 的用户
     */
    public int minPathSum(int[][] grid) {
        cache = new int[grid.length][grid[0].length];
        int min = recur(grid, 0, 0);
        return min;
    }

    private int[][] cache;

    /**
     * 递归，获取以 i，j 位置为起点的最短路径
     */
    private int recur(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) {
            return 0;
        }
        // 查缓存
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        // 只能往右
        if (i == grid.length - 1) {
            int right = recur(grid, i, j + 1);
            return right + grid[i][j];
        }
        // 只能往下
        if (j == grid[0].length - 1) {
            int down = recur(grid, i + 1, j);
            return down + grid[i][j];
        }
        // 往右或往下
        int down = recur(grid, i + 1, j);
        int right = recur(grid, i, j + 1);
        int result = Math.min(down, right) + grid[i][j];
        // 记录缓存
        cache[i][j] = result;
        return result;
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
