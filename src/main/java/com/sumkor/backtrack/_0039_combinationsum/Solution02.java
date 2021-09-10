package com.sumkor.backtrack._0039_combinationsum;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/9/10
 */
public class Solution02 {

    /**
     * 回溯法
     *
     * 总体思想：在选择了 i 位置的前提下，进入递归，依旧从当前 i 位置开始选择，直到达到或超过 target 值
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.78% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了71.15% 的用户
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        recur(candidates, target, 0, 0, path, ans);
        return ans;
    }

    /**
     * 由于数组 candidates 是提前排好顺序的
     * 对于 candidates = [2,3,6,7], target = 7
     * 在已知 [2,2,2,2] 满足 target 的情况下，下一次需要控制不再选择 [2,2,2,3]
     */
    private boolean recur(int[] candidates, int target, int index, int sum, LinkedList<Integer> path, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return true;
        }
        if (sum > target) {
            return true;
        }
        for (int i = index; i < candidates.length; i++) {
            // 1. 做出选择
            path.add(candidates[i]);
            sum += candidates[i];
            System.out.println("递归前 》 " + path);

            // 2. 进入递归：在选择当前 i 位置的前提下，下一次还是从 i 位置开始选择
            boolean result = recur(candidates, target, i, sum, path, ans);
            if (result) {
                // 在前几次选择的基础上，如果当前选择的 i 已经到头了，那么没有必要进入下一次循环选择 i + 1 了
                i = candidates.length - 1;
                // 但是，这里还是需要进行撤销选择！把 path 复原到选择当前 i 之前，注意这里对 sum 的回溯错误并不要紧，因为不会进入下一次循环了
            }

            // 3. 撤销选择
            path.removeLast();
            sum = sum - candidates[i];
            System.out.println("递归后 》 " + path);
        }
        return false;
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
        int[] candidates = new int[]{2, 3, 5}; int target = 8;
//        int[] candidates = new int[]{2}; int target = 1;

        List<List<Integer>> lists = combinationSum(candidates, target);
        System.out.println("lists = " + lists);
    }
}
