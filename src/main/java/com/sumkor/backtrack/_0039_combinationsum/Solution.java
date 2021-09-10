package com.sumkor.backtrack._0039_combinationsum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/10
 */
public class Solution {

    /**
     * 回溯法
     *
     * 总体思想：在选择了 i 位置的前提下，进入递归，依旧从当前 i 位置开始选择，直到达到或超过 target 值
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了41.32% 的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了6.55% 的用户
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        // Arrays.sort(candidates); // 未剪枝时，不需要提前排序
        recur(candidates, target, 0, 0, path, ans);
        return ans;
    }

    /**
     * 本解法的缺陷在于：
     * 对于 candidates = [2,3,6,7], target = 7
     * 在已知 [2,2,2,2] 满足 target 的情况下，下一次会选择 [2,2,2,3]，再判断该选择不满足 target，这一步是无效的。
     * 需要剪枝。
     */
    private void recur(int[] candidates, int target, int index, int sum, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 做出选择
            path.add(candidates[i]);
            sum += candidates[i];
            System.out.println("递归前 》 " + path);

            // 进入递归：在选择当前 i 位置的前提下，下一次还是从 i 位置开始选择
            recur(candidates, target, i, sum, path, ans);

            // 撤销选择
            path.removeLast();
            sum = sum - candidates[i];
            System.out.println("递归后 》 " + path);
        }
    }

    /**
     * 输入: candidates = [2,3,6,7], target = 7
     * 输出: [[7],[2,2,3]]
     *
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     *
     * 输入: candidates = [2], target = 1
     * 输出: []
     */
    @Test
    public void test() {
//        int[] candidates = new int[]{2, 3, 6, 7}; int target = 7;
//        int[] candidates = new int[]{2, 3, 5}; int target = 8;
        int[] candidates = new int[]{2, 7, 6, 3, 5, 1}; int target = 9;
//        int[] candidates = new int[]{2}; int target = 1;

        List<List<Integer>> lists = combinationSum(candidates, target);
        System.out.println("lists = " + lists);
    }
}
