package com.sumkor.backtrack._0077_combine;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/2
 */
public class Solution {

    /**
     * 暴力法
     *
     * 执行用时：41 ms, 在所有 Java 提交中击败了8.01% 的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了12.88% 的用户
     */
    public List<List<Integer>> combine(int n, int k) {
        commonK = k;
        for (int i = 1; i <= n; i++) {
            recur(n, k, i, new ArrayList<>());
        }
        return results;
    }

    List<List<Integer>> results = new ArrayList<>();
    int commonK;

    /**
     * 1. 在当前 list 集合的基础上，追加当前 index 位置的值作为新的 list
     * 2. 遍历 index 位置后的每一位，进入递归：
     *    在下一层递归中，追加新的 index 值作为新 list
     *    递归的深度为 k
     */
    private void recur(int n, int k, int index, List<Integer> list) {
        if (k == 0) {
            return;
        }
        List<Integer> copyList = new ArrayList<>(list);
        copyList.add(index);
        if (copyList.size() == commonK) {
            results.add(copyList);
        }
        for (int i = index + 1; i <= n; i++) {
            recur(n, k - 1, i, copyList);
        }
    }

    /**
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     */
    @Test
    public void test() {
        List<List<Integer>> results = combine(4, 4);
        System.out.println("results = " + results);
    }

    @Test
    public void listArray() {
        List<Integer>[][] cache = new ArrayList[5][3];
        System.out.println("cache = " + cache);
        System.out.println("cache = " + cache[2][0]);
        if (cache[1][2] != null) {
            cache[1][2] = new ArrayList<>();
        }
    }
}
