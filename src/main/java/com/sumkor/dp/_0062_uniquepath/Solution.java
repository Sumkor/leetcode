package com.sumkor.dp._0062_uniquepath;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/12
 */
public class Solution {

    /**
     * 暴力递归
     *
     * 返回以 [m][n] 为终点的不同路径的数量。
     * 之所以是终点，因为依赖项是 m - 1 和 n - 1。
     *
     * 超出时间限制
     */
    public int uniquePaths(int m, int n) {
        if (m == 1) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        // 机器人每次只能向下或者向右移动一步
        int i = uniquePaths(m - 1, n);
        int j = uniquePaths(m, n - 1);
        return i + j;
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
