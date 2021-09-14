package com.sumkor.backtrack._0040_combinationsum;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/9/10
 */
public class Solution {

    /**
     * 回溯法
     *
     * 总体思想：
     * 1. 在选择了 i 位置的前提下，进入递归，从当前 i+1 位置开始选择，直到达到或超过 target 值
     * 2. 撤销 i 位置的选择，进入下一次循环，选择 i+1
     *
     * 剪枝：
     * 1. 先进行排序，在递归选择过程中，如果发现当前选择已经满足 target 要求，则不必进入下一次循环，因为下一次循环只会选出超过 target 的值！
     * 2. 由于 candidates 数组中包含重复的元素，需要记录每一个位置是否在 path 已选，避免重复
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了63.63% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了84.16% 的用户
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> ans = new HashSet<>();
        LinkedList<Integer> path = new LinkedList<>();
        // 排序，用于剪枝
        Arrays.sort(candidates);
        // 数组，记录当前位是否已选
        boolean[] map = new boolean[candidates.length];
        recur(candidates, target, 0, 0, map, path, ans);
        return new ArrayList<>(ans);
    }

    private boolean recur(int[] candidates, int target, int index, int sum, boolean[] map, LinkedList<Integer> path, Set<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return true;
        }
        if (sum > target) {
            return true;
        }
        for (int i = index; i < candidates.length; i++) {
            // 剪枝：由于数组中包含重复元素，上一位没有选择的情况下，当前位与上一位相等，则当前位也不选
            if (i > 0 && !map[i - 1] && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 1. 做出选择
            path.add(candidates[i]);
            sum += candidates[i];
            map[i] = true;
            // System.out.println("递归前 》 " + path);

            // 2. 进入递归，选择下一位
            boolean next = recur(candidates, target, i + 1, sum, map, path, ans);

            // 3. 撤销选择，注意这里需要回溯 path、map[]、sum
            path.removeLast();
            sum = sum - candidates[i];
            map[i] = false;
            // System.out.println("递归后 》 " + path);

            // 剪枝：当前选择已经到头了，不必进入下一次循环
            if (next) {
                i = candidates.length;
            }
        }
        return false;
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
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5}; int target = 8;
//        int[] candidates = new int[]{2, 5, 2, 1, 2}; int target = 5;
//        int[] candidates = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}; int target = 30;

        long start = System.currentTimeMillis();
        List<List<Integer>> lists = combinationSum2(candidates, target);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("lists = " + lists);
    }

}
