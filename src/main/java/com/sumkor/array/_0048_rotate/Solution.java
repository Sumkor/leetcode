package com.sumkor.array._0048_rotate;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2021/10/8
 */
public class Solution {

    /**
     * 原地算法
     * 1. 左右翻转
     * 2. 对角线翻转
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *   ↓
     * 3 2 1
     * 6 5 4
     * 9 8 7
     *   ↓
     * 7 4 1
     * 8 5 2
     * 9 6 3
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了5.35% 的用户
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 左右翻转
        for (int i = 0; i < n; i++) {
            revert(matrix[i], 0, n - 1);
        }
        // 对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }
    }

    public void revert(int[] array, int left, int right) {
        while (left < right) {
            int temp = array[right];
            array[right] = array[left];
            array[left] = temp;
            left++;
            right--;
        }
    }

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     *
     * 时间复杂度：O(N^2)，其中 N 是 matrix 的边长。对于每一次翻转操作，我们都需要枚举矩阵中一半的元素。
     * 空间复杂度：O(1)。为原地翻转得到的原地旋转。
     */
    public void rotate0(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[[7,4,1],[8,5,2],[9,6,3]]
     *
     * 输入：matrix = [[1,2],[3,4]]
     * 输出：[[3,1],[4,2]]
     */
    @Test
    public void test01() {
        int[][] matrix = new int[3][3];
        matrix[0] = new int[]{1,2,3};
        matrix[1] = new int[]{4,5,6};
        matrix[2] = new int[]{7,8,9};
        rotate(matrix);
        System.out.println("matrix = " + matrix);
    }

    /**
     * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
     * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
     */
    @Test
    public void test02() {
        int[][] matrix = new int[4][4];
        matrix[0] = new int[]{5,1,9,11};
        matrix[1] = new int[]{2,4,8,10};
        matrix[2] = new int[]{13,3,6,7};
        matrix[3] = new int[]{15,14,12,16};
        rotate(matrix);
        System.out.println("matrix = " + matrix);
    }

    @Test
    public void array() {
        int[][] array = new int[2][3];
        array[0] = new int[]{1,2,3};
        array[1] = new int[]{2,3,4};
        System.out.println("array = " + array);

        int length = array.length;
        System.out.println("length = " + length); // 2
    }
}
