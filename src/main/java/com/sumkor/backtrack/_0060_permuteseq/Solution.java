package com.sumkor.backtrack._0060_permuteseq;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/14
 */
public class Solution {

    /**
     * 回溯算法
     *
     * 执行用时：685 ms, 在所有 Java 提交中击败了17.44% 的用户
     * 内存消耗：110.8 MB, 在所有 Java 提交中击败了5.03% 的用户
     */
    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        LinkedList<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] map = new boolean[n + 1];
        // 记录当前排列的次序
        count = k;
        recur(n, map, path, res);
        // 最后一个，即为题目所需的排列
        List<Integer> permutation = res.getLast();
        for (Integer integer : permutation) {
            ans.append(integer);
        }
        return ans.toString();
    }

    private int count;

    private void recur(int n,boolean[] map, LinkedList<Integer> path, List<List<Integer>> res) {
        if (count == 0) {
            return;
        }
        if (path.size() == n) {
            res.add(new ArrayList<>(path));
            count--;
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!map[i]) {
                path.add(i);
                map[i] = true;

                recur(n, map, path, res);

                path.removeLast();
                map[i] = false;
            }
        }
    }

    /**
     * 输入：n = 3, k = 1
     * 输出："123"
     *
     * 输入：n = 3, k = 3
     * 输出："213"
     *
     * 输入：n = 4, k = 9
     * 输出："2314"
     */
    @Test
    public void test() {
        String permutation = getPermutation(3, 3);
        System.out.println("permutation = " + permutation);
    }
}
