package com.sumkor.backtrack._0078_subsets;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Sumkor
 * @since 2021/8/28
 */
public class Solution {

    /**
     * 回溯法（删除法）
     * 使用 HashMap 作为结果集，减少去重的耗时
     *
     * 执行用时：3 ms, 在所有 Java 提交中击败了8.78% 的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了87.60% 的用户
     */
    public List<List<Integer>> subsets0(int[] nums) {
        List<Integer> inputList = new ArrayList<>();
        for (int num : nums) {
            inputList.add(num);
        }
        HashSet<List<Integer>> outputSet = new HashSet<>();
        recur(inputList, outputSet);
        return new ArrayList<>(outputSet);
    }

    /**
     * 递归移除 inputList 中的其中一位，加入结果集
     */
    private void recur(List<Integer> inputList, HashSet<List<Integer>> outputSet) {
        if (!outputSet.contains(inputList)) {
            outputSet.add(inputList);
            // 移除 inputList 中的其中一位，进入递归
            for (int i = 0; i < inputList.size(); i++) {
                List<Integer> copyList = new ArrayList<>(inputList);
                copyList.remove(i);
                recur(copyList, outputSet);
            }
        }
    }

    /**
     * 回溯法（添加法，最优解）
     * https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/
     *
     * 回溯法（探索与回溯法）是一种选优搜索法，又称为试探法，按选优条件向前搜索，以达到目标。
     * 但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择。
     * 这种走不通就退回再走的技术为回溯法，而满足回溯条件的某个状态的点称为“回溯点”。
     *
     * 按照 nums 本身的顺序来选择，也就是先选了 nums[i] 后再选 nums[j]，满足 i<j。
     * 由于题目中，数组中的元素【互不相同】，因此可以避免去重导致的耗时。
     *
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了42.08% 的用户
     *
     * 时间复杂度：O(n×2^n)。原序列的每个位置在答案序列中的状态有被选中和不被选中两种，即一共 2^n 个状态。每种状态需要 O(n) 的时间来构造子集。
     * 空间复杂度：O(n)。临时数组 t 的空间代价是 O(n)，递归时栈空间的代价为 O(n)。
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        backtrack(nums, results, combine, 0);
        return results;
    }

    /**
     * 1. 做出选择：combine 作为选择列表，选择 nums 中的 idx 位置
     * 2. 进入递归：子集问题的选择列表，是上一条选择路径之后的数
     * 3. 撤销选择：combine 列表中去掉当前选择的 idx 位置的数值，便于进入下一次循环
     */
    private void backtrack(int[] nums, List<List<Integer>> results, List<Integer> combine, int idx) {
        results.add(new ArrayList<>(combine)); // 这里必须用新对象包装
        for (int i = idx; i < nums.length; i++) {
            combine.add(nums[i]);
            backtrack(nums, results, combine, i + 1);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */
    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println("subsets = " + subsets);
    }

    @Test
    public void equals() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);

        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(1);

        System.out.println(list1.equals(list2)); // true
        System.out.println("list1 = " + list1.hashCode());
        System.out.println("list2 = " + list2.hashCode());

        System.out.println(list1.equals(list3)); // false
    }
}
