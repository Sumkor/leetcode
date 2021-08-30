package com.sumkor.backtrack._0090_subsets;

import org.junit.Test;

import java.util.*;

/**
 * @author Sumkor
 * @since 2021/8/30
 */
public class Solution {

    /**
     * 回溯法（添加法，使用排序 + HashMap 来去重）
     *
     * 回溯之前先排序，避免子集重复。
     * 例如由入参 [4,4,4,1,4] 得到 [4,4,1] 和 [4,1,4] 这样重复的子集。
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了43.26% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了50.13% 的用户
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> results = new HashSet<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(nums);
        backtrace(nums, 0, results, combine);
        return new ArrayList<>(results);
    }

    public void backtrace(int[] nums, int idx, Set<List<Integer>> results, List<Integer> combine) {
        if (!results.contains(combine)) {
            results.add(new ArrayList<>(combine));
            for (int i = idx; i < nums.length; i++) {
                combine.add(nums[i]);
                backtrace(nums, i + 1, results, combine);
                combine.remove(combine.size() - 1);
            }
        }
    }

    /**
     * 回溯法（删除法，使用排序 + HashMap 来去重）
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了11.32% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了54.39% 的用户
     */
    public List<List<Integer>> subsetsWithDup0(int[] nums) {
        Set<List<Integer>> results = new HashSet<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(nums);
        for (int num : nums) {
            combine.add(num);
        }
        recur(results, combine);
        return new ArrayList<>(results);
    }

    public void recur(Set<List<Integer>> results, List<Integer> combine) {
        if (!results.contains(combine)) {
            results.add(combine);
            for (int i = 0; i < combine.size(); i++) {
                List<Integer> copyList = new ArrayList<>(combine);
                copyList.remove(i);
                recur(results, copyList);
            }
        }
    }

    /**
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 2};
        List<List<Integer>> lists = subsetsWithDup(nums);
        System.out.println("lists = " + lists);
    }

    /**
     * [4,4,4,1,4]
     * [[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]
     */
    @Test
    public void test01() {
        int[] nums = new int[]{4, 4, 4, 1, 4};
        List<List<Integer>> lists = subsetsWithDup(nums);
        System.out.println("lists = " + lists);
    }

}
