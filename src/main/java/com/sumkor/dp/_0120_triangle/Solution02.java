package com.sumkor.dp._0120_triangle;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/22
 */
public class Solution02 {

    /**
     * 暴力递归 + 缓存（记忆化搜索）
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了95.03% 的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了36.68% 的用户
     *
     * {@link TestCase#testLong()} 耗时 12 ms
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        cache = new ArrayList<>(triangle.size());
        int result = recur(triangle, 0, 0);
        return result;
    }

    /**
     * 缓存，记录每一层的结果
     */
    private List<Map<Integer, Integer>> cache;

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
        if (i >= cache.size()) {
            cache.add(new HashMap<>());
        }
        // 查缓存
        Map<Integer, Integer> map = cache.get(i);
        if (map.containsKey(j)) {
            return map.get(j);
        }
        int currNum = triangle.get(i).get(j);
        int left = recur(triangle, i + 1, j);
        int right = recur(triangle, i + 1, j + 1);
        int nextMin = Math.min(left, right);
        int result = currNum + nextMin;
        // 记录缓存
        map.put(j, result);
        return result;
    }
}
