package com.sumkor.backtrack._0040_combinationsum;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/10
 */
public class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> ans = new HashSet<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        recur(candidates, target, 0, 0, path, ans);
        return new ArrayList<>(ans);
    }

    private void recur(int[] candidates, int target, int index, int sum, LinkedList<Integer> path, Set<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            // System.out.println("递归前 》 " + path);

            recur(candidates, target, i + 1, sum, path, ans);

            path.removeLast();
            sum = sum - candidates[i];
            // System.out.println("递归后 》 " + path);
        }
    }

    /**
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出: [[1,1,6],[1,2,5],[1,7],[2,6]]
     *
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出: [[1,2,2],[5]]
     *
     * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
     * 30
     */
    @Test
    public void test() {
//        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5}; int target = 8;
//        int[] candidates = new int[]{2, 5, 2, 1, 2}; int target = 5;
        int[] candidates = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}; int target = 30;

        long start = System.currentTimeMillis();
        List<List<Integer>> lists = combinationSum2(candidates, target);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("lists = " + lists);
    }

}
