package com.sumkor.dp._0062_uniquepath;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/12
 */
public class Solution02 {

    /**
     * 记忆化搜索
     *
     * 返回以 [m][n] 为终点的不同路径的数量。
     * 之所以是终点，因为依赖项是 m - 1 和 n - 1。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：35.4 MB, 在所有 Java 提交中击败了5.76% 的用户
     */
    public int uniquePaths(int m, int n) {
        cache = new int[m + 1][n + 1];
        return doFindPath(m, n);
    }

    int[][] cache;

    public int doFindPath(int m, int n) {
        if (m == 1) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        // 命中缓存
        if (cache[m][n] != 0) {
            return cache[m][n];
        }
        // 机器人每次只能向下或者向右移动一步
        int i = doFindPath(m - 1, n);
        int j = doFindPath(m, n - 1);
        // 更新缓存
        cache[m][n] = i + j;
        return cache[m][n];
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
        int m = 3, n = 7;
        int paths = uniquePaths(m, n);
        System.out.println("paths = " + paths);
    }
}
