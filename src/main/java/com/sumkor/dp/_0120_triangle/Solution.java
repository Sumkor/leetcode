package com.sumkor.dp._0120_triangle;

import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/22
 */
public class Solution {

    /**
     * 暴力递归
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return recur(triangle, 0, 0);
    }

    /**
     * 递归，找出第 i 层的 j 位置的最短路径和
     *
     * 每一步只能移动到下一行中相邻的结点上。
     * 也就是说，如果正位于当前行的下标 j ，那么下一步可以移动到下一行的下标 j 或 j + 1 。
     */
    private int recur(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (j == triangle.get(i).size()) {
            return 0;
        }
        int currNum = triangle.get(i).get(j);
        // 分别获取下一层 j 和 j + 1 位置的最短路径和
        int left = recur(triangle, i + 1, j);
        int right = recur(triangle, i + 1, j + 1);
        int nextMin = Math.min(left, right);
        return currNum + nextMin;
    }

}
