package com.sumkor.array._0498_diagonal;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Sumkor
 * @since 2021/10/11
 */
public class Solution {

    /**
     * 1,2,3
     * 4,5,6
     * 7,8,9
     *
     *
     * [0][0]                0
     * [0][1] [1][0]         1   i递增 j递减
     * [2][0] [1][1] [0][2]  2   i递减 j递减
     * [1][2] [2][1]         3   i递增 j递减
     * [2][2]                4
     *
     * 规律：
     * 按层遍历的时候，下标之和等于当前的层数（从 0 开始算）
     * 当层数为偶数时，i 递减；当层数为奇数时，i 递增。
     * 难点在于，计算得出什么时候进入下一层，以及以何种方式进入下一层。
     * 这里的做法是，当 i 或 j 一方达到边界时，就可以进入下一层。
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了90.87% 的用户
     * 内存消耗：40.4 MB, 在所有 Java 提交中击败了60.00% 的用户
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int i = 0, j = 0;
        int index = 0;
        int level = 0;
        while (i < m && j < n) {
            ans[index] = mat[i][j];
            // 如果 i 需要递减
            if (level % 2 == 0) {
                // 进入下一层
                if (i - 1 < 0 || j + 1 == n) {
                    level++;
                    // 【难点】一般而言是需要 j 递增，但如果 j 已经到头了，就需要 i 递增
                    if (j + 1 == n) {
                        i++;
                    } else {
                        j++;
                    }
                }
                // 停留在当前层
                else {
                    i--;
                    j++;
                }
            }
            // 如果 i 需要递增
            else {
                // 进入下一层
                if (i + 1 == m || j - 1 < 0) {
                    level++;
                    // 【难点】一般而言是需要 i 递增，但如果 i 已经到头了，就需要 j 递增
                    if (i + 1 == m) {
                        j++;
                    } else {
                        i++;
                    }
                }
                // 停留在当前层
                else {
                    i++;
                    j--;
                }
            }
            index++;
        }
        return ans;
    }

    /**
     * 输入：mat =
     * [[1,2,3],
     *  [4,5,6],
     *  [7,8,9]]
     * 输出：[1,2,4,7,5,3,6,8,9]
     *
     * 输入：mat =
     * [[1,2],
     *  [3,4]]
     * 输出：[1,2,3,4]
     */
    @Test
    public void test() {
        int[][] mat = new int[3][3];
        mat[0] = new int[]{1,2,3};
        mat[1] = new int[]{4,5,6};
        mat[2] = new int[]{7,8,9};
        int[] order = findDiagonalOrder(mat);
        Arrays.stream(order).forEach(System.out::print);
    }

    @Test
    public void test2() {
        int[][] mat = new int[2][3];
        mat[0] = new int[]{1,2,3};
        mat[1] = new int[]{4,5,6};
        int[] order = findDiagonalOrder(mat);
        Arrays.stream(order).forEach(System.out::print);
    }
}
