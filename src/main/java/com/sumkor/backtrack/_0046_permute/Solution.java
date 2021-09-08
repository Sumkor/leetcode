package com.sumkor.backtrack._0046_permute;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/8
 */
public class Solution {

    /**
     * 递归树
     *            1,2,3
     *          /   |    \
     *      2,3    1,3    1,2
     *      / \    / \    / \
     *     3   2  3   1  2   1
     * (1,2,3)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>(); // 当前路径
        Map<Integer, Boolean> map = new HashMap<>();   // 统计已加入的数据
        return ans;
    }

    private void recur(int index, int[] nums, Map<Integer, Boolean> map, LinkedList<Integer> path, List<List<Integer>> ans) {

    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     *
     * 输入：nums = [1]
     * 输出：[[1]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> results = permute(nums);
        System.out.println("results = " + results);
    }
}
